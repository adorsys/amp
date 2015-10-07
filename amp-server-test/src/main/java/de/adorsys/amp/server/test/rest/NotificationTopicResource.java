/**
 * Copyright (C) 2015 Sandro Sonntag (sso@adorsys.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
