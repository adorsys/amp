package de.adorsys.amp.server.test.rest;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import de.adorsys.amp.server.test.service.DeviceRegistrationService;
import de.adorsys.amp.server.test.service.NotificationTopicService;
import de.adorsys.amp.server.test.service.PushMessage;

@Path("notification-topic")
@Singleton
public class NotificationTopicResource {

	@Inject
	NotificationTopicService notificationTopicService;
	
	@Inject
	DeviceRegistrationService deviceRegistrationService;
	
	@PUT
	@Path("{topic}/device-regs/{registrationId}")
	public void addDeviceRegistrationForTopic(@PathParam("topic") String topic, @PathParam("registrationId") String registrationId) {
		deviceRegistrationService.addRegistraionForTopic(topic, registrationId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{topic}")
	public void publish(@PathParam("topic") String topic, PushMessage message) {
		notificationTopicService.publish(topic, message);
	}
}
