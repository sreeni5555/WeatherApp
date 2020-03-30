package com.loginradius.weather.exception;

/**
 * @author Sri
 * Through this exception when a city is not found for the input given
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends RuntimeException {

	/**
	 * 
	 */	
	private static final long serialVersionUID = -8019833419499477933L;

	public CityNotFoundException(String exception) {
		super(exception);
	}

}