package de.adorsys.amp.gcm.client;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

public class GCMServiceTest {

	@Test
	public void testSendNotification() {
		GCMService gcmService = new GCMService();
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("message", "hello world");
		Booking booking = new Booking();
		booking.setAccountNumber("88888888");
		booking.setAmount(new BigDecimal("999.11"));
		booking.setDescription("this is a demo booking");
		hashMap.put("complexObject", booking);
		try {
			gcmService.sendNotification(hashMap, "APA91bFj_QUSCL6RnwZ_uz8SzvjAWWxApsU7vxtz8R5jn-rg1nZnhRri2MU9FWhDq0oOdiRZ8rIpZ5B_ma2ZNhPjTMOwocImjo5PIU9kVkaxop0bveYP1WvEZxKIrRpmJ6rVORm-A5vT");
		} catch (UnknownRegistrationIdException | NotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
