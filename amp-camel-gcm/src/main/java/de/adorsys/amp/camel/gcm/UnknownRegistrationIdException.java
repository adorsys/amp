/**
 * 
 */
package de.adorsys.amp.camel.gcm;

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
}
