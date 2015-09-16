package de.adorsys.amp.server.test.service;

import java.util.Collection;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.adorsys.amp.gcm.client.GCMService;
import de.adorsys.amp.gcm.client.NotRegisteredException;
import de.adorsys.amp.gcm.client.UnknownRegistrationIdException;

@ApplicationScoped
public class NotificationTopicService {
	
	@Inject
	GCMService gcmService;
	@Inject
	DeviceRegistrationService deviceRegistrationService;

	public void publish(String topic, Map<String, Object> message) {
		Collection<String> registrationIdsForTopic = deviceRegistrationService.getRegistrationIdsForTopic(topic);
		try {
			gcmService.sendNotification(message, registrationIdsForTopic.toArray(new String[0]));
		} catch (NotRegisteredException e) {
			deviceRegistrationService.removeRegistrationFromTopic(topic, e.getRegistrationId());
		} catch (UnknownRegistrationIdException e) {
			deviceRegistrationService.removeRegistrationFromTopic(topic, e.getRegistrationId());
		}
		
	}

}
