package com.sg.shop.exception;

public class BillingServiceException extends RuntimeException {

	private static final long serialVersionUID = 7103611849315754838L;

	public BillingServiceException() {
		super();
	}

	public BillingServiceException(String message) {
		super(message);
	}

	public BillingServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public BillingServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BillingServiceException(Throwable cause) {
		super(cause);
	}

}
