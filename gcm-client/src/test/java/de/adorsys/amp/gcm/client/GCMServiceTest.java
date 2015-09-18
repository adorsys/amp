package de.adorsys.amp.gcm.client;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import de.adorsys.amp.gcm.client.GCMMessage.GCMNotification;

public class GCMServiceTest {

	@Test
	public void testSendNotification() {
		GCMService gcmService = new GCMService();
		HashMap<String, Object> data = new HashMap<>();
		data.put("message", "hello world");
		Booking booking = new Booking();
		booking.setAccountNumber("88888888");
		booking.setAmount(new BigDecimal("999.11"));
		booking.setDescription("this is a demo booking");
		data.put("complexObject", booking);
		
		GCMNotification notification = new GCMNotification();
		notification.setTitle("My Message");
		notification.setBody("This is a TAN");
		notification.setIcon("myicon");
		try {
			gcmService.sendNotification(notification, data, "AIzaSyAju6wApRVHMXbg7qJ1QpEAYPu3TikPook", "APA91bFj_QUSCL6RnwZ_uz8SzvjAWWxApsU7vxtz8R5jn-rg1nZnhRri2MU9FWhDq0oOdiRZ8rIpZ5B_ma2ZNhPjTMOwocImjo5PIU9kVkaxop0bveYP1WvEZxKIrRpmJ6rVORm-A5vT");
		} catch (UnknownRegistrationIdException | NotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
