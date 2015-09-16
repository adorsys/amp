/**
 * 
 */
package de.adorsys.amp.camel.gcm;

/**
 * @author sso
 *
 */
public class GCMException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GCMException(String message, Throwable cause) {
		super(message, cause);
	}

	public GCMException(String message) {
		super(message);
	}

	public GCMException(Throwable cause) {
		super(cause);
	}

}
