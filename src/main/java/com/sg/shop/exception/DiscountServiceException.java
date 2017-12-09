package com.sg.shop.exception;

public class DiscountServiceException extends RuntimeException {

	private static final long serialVersionUID = -3612354085652027064L;

	public DiscountServiceException() {
		super();
	}

	public DiscountServiceException(String message) {
		super(message);
	}

	public DiscountServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DiscountServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DiscountServiceException(Throwable cause) {
		super(cause);
	}

}
