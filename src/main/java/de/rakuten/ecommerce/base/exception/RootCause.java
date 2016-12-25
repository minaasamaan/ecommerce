/**
 * 
 */
package de.rakuten.ecommerce.base.exception;

/**
 * Parent for all root causes in the application, any application specific
 * exception must have an implementation for the root cause to be used in
 * logging & exception reporting.
 * 
 * @author Mina
 *
 */
public interface RootCause {

	enum RootCauseType {
		TECHNICAL_ERROR, BUSINESS_ERROR
	}

	String getMessage();

	RootCauseType getRootCauseType();

	default String formattedToString() {
		return new StringBuilder(getRootCauseType().name()).append(": ").append(getMessage()).toString();
	}

}
