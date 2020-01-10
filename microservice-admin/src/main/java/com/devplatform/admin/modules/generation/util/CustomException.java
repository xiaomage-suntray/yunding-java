package com.devplatform.admin.modules.generation.util;

public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1958246937922915228L;

	private String errCode;

	public CustomException(String msg, String errCode) {
		super(msg);
		this.errCode = errCode;
	}


	public CustomException(String msg, String errCode, Throwable obj) {
		super(msg, obj);
		this.errCode = errCode;
	}

	public CustomException(String message, String errCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errCode = errCode;
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}

	@SuppressWarnings("unused")
	private CustomException() {
		super();
	}


	@SuppressWarnings("unused")
	private  CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	@SuppressWarnings("unused")
	private CustomException(String message) {
		super(message);
	}

	@SuppressWarnings("unused")
	private CustomException(Throwable cause) {
		super(cause);
	}

}