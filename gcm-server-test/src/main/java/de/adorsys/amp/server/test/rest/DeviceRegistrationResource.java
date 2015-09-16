/**
 * 
 */
package de.adorsys.amp.server.test.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import de.adorsys.amp.server.test.service.DeviceRegistrationService;

/**
 * @author sso
 *
 */
@Path("/push-message-topic/{topic}/{registrationId}")
@Singleton
public class DeviceRegistrationResource {

	@Inject
	DeviceRegistrationService registrationService;
	
	@PUT
	public void addRegistraionForTopic(@PathParam("topic") String topic, @PathParam("registrationId") String registrationId) {
		registrationService.addRegistraionForTopic(topic, registrationId);
	}
}
