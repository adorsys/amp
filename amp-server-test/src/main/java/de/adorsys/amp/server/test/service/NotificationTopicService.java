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
