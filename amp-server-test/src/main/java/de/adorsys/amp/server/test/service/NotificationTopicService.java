package de.adorsys.amp.server.test.service;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.adorsys.amp.gcm.client.GCMService;
import de.adorsys.amp.gcm.client.NotRegisteredException;
import de.adorsys.amp.gcm.client.UnknownRegistrationIdException;

@ApplicationScoped
public class NotificationTopicService {
	
	private static final String GOOGLE_API_KEY = "AIzaSyAju6wApRVHMXbg7qJ1QpEAYPu3TikPook";

	@Inject
	GCMService gcmService;
	
	@Inject
	DeviceRegistrationService deviceRegistrationService;

	public void publish(String topic, PushMessage message) {
		Collection<String> registrationIdsForTopic = deviceRegistrationService.getRegistrationIdsForTopic(topic);
		try {
			gcmService.sendNotification(message.getNotification(), message.getData(), GOOGLE_API_KEY, registrationIdsForTopic.toArray(new String[0]));
		} catch (NotRegisteredException e) {
			deviceRegistrationService.removeRegistrationFromTopic(topic, e.getRegistrationId());
		} catch (UnknownRegistrationIdException e) {
			deviceRegistrationService.removeRegistrationFromTopic(topic, e.getRegistrationId());
		}
		
	}

}
