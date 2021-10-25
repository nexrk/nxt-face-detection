/*
 *
 * Author: Radhakrishnan
 *
 */
package com.nxt.faceapp.exception;

/**
 * The Class StorageException.
 */
public class StorageException extends RuntimeException {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new storage exception.
	 *
	 * @param message the message
	 */
	public StorageException(String message) {
        super(message);
    }

    /**
     * Instantiates a new storage exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

