package de.adorsys.amp.camel.gcm;

import java.util.HashMap;

import org.junit.Test;

public class GCMServiceTest {

	@Test
	public void testSendNotification() {
		GCMService gcmService = new GCMService();
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("message", "hello world");
		try {
			gcmService.sendNotification2(hashMap, "1", "APA91bFj_QUSCL6RnwZ_uz8SzvjAWWxApsU7vxtz8R5jn-rg1nZnhRri2MU9FWhDq0oOdiRZ8rIpZ5B_ma2ZNhPjTMOwocImjo5PIU9kVkaxop0bveYP1WvEZxKIrRpmJ6rVORm-A5vT");
		} catch (UnknownRegistrationIdException | NotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
