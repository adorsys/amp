package de.adorsys.amp.gcm.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.adorsys.amp.gcm.client.GCMMessage.GCMNotification;
import de.adorsys.amp.gcm.client.GCMResults.GCMResult;

@ApplicationScoped
public class GCMService {
	
	private static final Logger LOG = LoggerFactory.getLogger(GCMService.class);
	
	private static com.fasterxml.jackson.databind.ObjectMapper OM = new com.fasterxml.jackson.databind.ObjectMapper();
	
	static {
		Unirest.setObjectMapper(new ObjectMapper() {
			
			@Override
			public String writeValue(Object value) {
				try {
					return OM.writeValueAsString(value);
				} catch (JsonProcessingException e) {
					throw new GCMException("Connection problem sending GCM Message", e);
				}
			}
			
			@Override
			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return OM.readValue(value, valueType);
				} catch (IOException e) {
					throw new GCMException("Connection problem sending GCM Message", e);
				}
			}
		});
	}
	
	public void sendNotification(GCMNotification notification, Map<String, Object> data, String gcmApiKey, String... registrationIds) throws UnknownRegistrationIdException, NotRegisteredException {
		GCMMessage gcmMessage = new GCMMessage();
		
		Map<String, String> converted = new HashMap<>();
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			try {
				converted.put(entry.getKey(), OM.writeValueAsString(entry.getValue()));
			} catch (JsonProcessingException e) {
				throw new GCMException("problem on selialization of "+ entry.getValue());
			}
		}
		gcmMessage.setData(converted);
		gcmMessage.setRegistrationIds(registrationIds);
		gcmMessage.setNotification(notification);
		try {
			HttpResponse<GCMResults> results = Unirest.post("https://gcm-http.googleapis.com/gcm/send")
					  .header("Authorization", "key=" + gcmApiKey)
					  .header("Content-Type", "application/json")
					  .header("accept", "application/json")
					  .body(gcmMessage)
					  .asObject(GCMResults.class);
			List<GCMResult> resultList = results.getBody().getResults();
			for (GCMResult gcmResult : resultList) {
				if ("InvalidRegistration".equals(gcmResult.getError())){
					throw new UnknownRegistrationIdException(registrationIds[0]);
				} else if ("NotRegistered".equals(gcmResult.getError())) {
					throw new NotRegisteredException(registrationIds[0]);
				}
			}
		} catch (UnirestException e) {
			throw new GCMException("Connection problem sending GCM Message", e);
		}
	}

}
