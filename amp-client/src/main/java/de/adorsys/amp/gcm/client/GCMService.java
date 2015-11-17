package de.adorsys.amp.gcm.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

import de.adorsys.amp.gcm.client.GCMMessage.GCMNotification;
import de.adorsys.amp.gcm.client.GCMResults.GCMResult;

@Singleton
public class GCMService {

	private HttpHost proxyHost;
	private ObjectMapper objectMapper;

	@PostConstruct
	public void postConstruct() {
		String proxyHostUrl = getEnv("GCM_PROXY_HOST");
		String proxyPort = getEnv("GCM_PROXY_PORT");

		if (proxyHostUrl != null) {
			if (proxyPort != null) {
				proxyHost = new HttpHost(proxyHostUrl, Integer.valueOf(proxyPort));
			} else {
				proxyHost = new HttpHost(proxyHostUrl);
			}
		}

		final com.fasterxml.jackson.databind.ObjectMapper OM = new com.fasterxml.jackson.databind.ObjectMapper();
		objectMapper = new ObjectMapper() {

			@Override
			public String writeValue(Object value) {
				try {
					return OM.writeValueAsString(value);
				} catch (JsonProcessingException e) {
					throw new GCMException("problem on serialization of " + value);
				}
			}

			@Override
			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return OM.readValue(value, valueType);
				} catch (IOException e) {
					throw new GCMException("problem on deserialization of " + value);
				}
			}
		};

		Unirest.setObjectMapper(objectMapper);
	}

	public void sendNotification(GCMNotification notification, Map<String, Object> data, String gcmApiKey, String... registrationIds)
			throws UnknownRegistrationIdException, NotRegisteredException {
		GCMMessage gcmMessage = new GCMMessage();

		Map<String, String> converted = new HashMap<>();
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			converted.put(entry.getKey(), objectMapper.writeValue(entry.getValue()));
		}
		gcmMessage.setData(converted);
		gcmMessage.setRegistrationIds(registrationIds);
		gcmMessage.setContentAvailable(true);
		gcmMessage.setPriority("high");
		gcmMessage.setNotification(notification);

		HttpPost httpPost = new HttpPost("https://gcm-http.googleapis.com/gcm/send");
		httpPost.addHeader("Authorization", "key=" + gcmApiKey);
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.addHeader("accept", "application/json");
		httpPost.setEntity(new StringEntity(objectMapper.writeValue(gcmMessage), "UTF-8"));

		try (CloseableHttpClient httpClient = HttpClients.custom().setProxy(proxyHost).build();
				CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);) {

			HttpResponse<GCMResults> results = new HttpResponse<>(closeableHttpResponse, GCMResults.class);

			// HttpResponse<GCMResults> results = Unirest.post("https://gcm-http.googleapis.com/gcm/send").header("Authorization", "key=" + gcmApiKey)
			// .header("Content-Type", "application/json").header("accept", "application/json").body(gcmMessage).asObject(GCMResults.class);
			List<GCMResult> resultList = results.getBody().getResults();
			for (GCMResult gcmResult : resultList) {
				if ("InvalidRegistration".equals(gcmResult.getError())) {
					throw new UnknownRegistrationIdException(registrationIds[0]);
				} else if ("NotRegistered".equals(gcmResult.getError())) {
					throw new NotRegisteredException(registrationIds[0]);
				}
			}
		} catch (IOException e) {
			throw new GCMException("Connection problem sending GCM Message", e);
		}
	}

	private String getEnv(String prop) {
		String env = System.getenv(prop);
		if (env == null || env.trim().isEmpty()) {
			env = System.getProperty(prop);
		}
		return env;
	}

}
