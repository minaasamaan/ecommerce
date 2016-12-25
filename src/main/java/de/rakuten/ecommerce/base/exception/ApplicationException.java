package de.rakuten.ecommerce.base.exception;

/**
 * Parent exception for all application exceptions
 * 
 * @author Mina
 *
 */
public abstract class ApplicationException extends RuntimeException {

	private Class<?> source;
	private Throwable wrappedException;
	private RootCause rootCause;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ApplicationException(Throwable exception, RootCause rootCause, Class<?> source) {
		setRootCause(rootCause);
		setSource(source);
		setWrappedException(exception);
	}

	/**
	 * @return the source
	 */
	public Class<?> getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(Class<?> source) {
		this.source = source;
	}

	/**
	 * @return the rootCause
	 */
	public RootCause getRootCause() {
		return rootCause;
	}

	/**
	 * @param rootCause
	 *            the rootCause to set
	 */
	public void setRootCause(RootCause rootCause) {
		this.rootCause = rootCause;
	}

	/**
	 * @return the wrappedException
	 */
	public Throwable getWrappedException() {
		return wrappedException;
	}

	/**
	 * @param wrappedException
	 *            the wrappedException to set
	 */
	public void setWrappedException(Throwable wrappedException) {
		this.wrappedException = wrappedException;
	}
}
