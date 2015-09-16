package de.adorsys.amp.server.test.service;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

@ApplicationScoped
public class DeviceRegistrationService {
	Multimap<String, String> registrationStore = HashMultimap.create();;

	public void addRegistraionForTopic(String topic, String registrationId) {
		registrationStore.put(topic, registrationId);
	}
	
	public Collection<String> getRegistrationIdsForTopic(String topic) {
		return registrationStore.get(topic);
	}

	public void removeRegistrationFromTopic(String topic, String registrationId) {
		registrationStore.get(topic).remove(registrationId);
	}

}
