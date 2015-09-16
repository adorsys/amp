package de.adorsys.amp.server.test.rest;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import de.adorsys.amp.server.test.service.NotificationTopicService;

@Path("notification-topic")
@Singleton
public class NotificationTopicResource {

	@Inject
	NotificationTopicService notificationTopicService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{topic}")
	public void publish(@PathParam("topic") String topic, Map<String, Object> message) {
		notificationTopicService.publish(topic, message);
	}
}
