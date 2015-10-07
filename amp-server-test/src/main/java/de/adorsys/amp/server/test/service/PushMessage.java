package de.adorsys.amp.server.test.service;

import java.util.Map;

import de.adorsys.amp.gcm.client.GCMMessage.GCMNotification;

public class PushMessage {
	
	private GCMNotification notification;
	private Map<String, Object> data;
	public GCMNotification getNotification() {
		return notification;
	}
	public void setNotification(GCMNotification notification) {
		this.notification = notification;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
