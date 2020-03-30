/**
 * Dell pc 1
 */
package com.loginradius.weather.exception;

/**
 * @author Sreeni
 *
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class APIBadRequestBodyException extends RuntimeException {

	public APIBadRequestBodyException(String exception) {
		super(exception);
	}

}