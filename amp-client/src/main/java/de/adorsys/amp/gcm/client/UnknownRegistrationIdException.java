/**
 * 
 */
package de.adorsys.amp.gcm.client;

/**
 * @author sso
 *
 */
public class UnknownRegistrationIdException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String registrationId;

	/**
	 * It is recommended to log all message ID values returned from GCM.
	 * 
	 * The message ID (HTTP only)
	 */
	private final String messageId;

	public UnknownRegistrationIdException(String registrationId, String messageId) {
		super();
		this.registrationId = registrationId;
		this.messageId = messageId;
	}

	@Deprecated
	public UnknownRegistrationIdException(String registrationId) {
		super();
		this.registrationId = registrationId;
		this.messageId = null;
	}

	@Override
	public String toString() {
		return super.toString() + ", REGISTRATION ID= " + registrationId + ", MESSAGE ID= " + messageId;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public String getMessageId() {
		return messageId;
	}

}
