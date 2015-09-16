/**
 * 
 */
package de.adorsys.amp.gcm.client;

/**
 * @author sso
 *
 */
public class UnknownRegistrationIdException extends Exception {
	private final String registrationId;

	public UnknownRegistrationIdException(String registrationId) {
		super();
		this.registrationId = registrationId;
	}
	@Override
	public String toString() {
		return super.toString() + " " + registrationId;
	}
	public String getRegistrationId() {
		return registrationId;
	}
}
