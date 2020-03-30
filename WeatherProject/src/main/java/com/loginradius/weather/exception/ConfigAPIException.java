package com.loginradius.weather.exception;

import org.springframework.http.HttpStatus;

/*
 * Exception which would be thrown for configuration issues.
 */
public class ConfigAPIException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigAPIException(HttpStatus statusCode,String statusMessage, String statusBody) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.statusBody = statusBody;
	}
	
	public ConfigAPIException(HttpStatus statusCode,String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	public ConfigAPIException(String statusMessage) {
		super();
		this.statusMessage = statusMessage;
	} 
	
	public ConfigAPIException() {
		// TODO Auto-generated constructor stub
	}

	private HttpStatus statusCode;
	private String statusMessage;
	private String statusBody;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getStatusBody() {
		return statusBody;
	}
	public void setStatusBody(String statusBody) {
		this.statusBody = statusBody;
	}	
  }