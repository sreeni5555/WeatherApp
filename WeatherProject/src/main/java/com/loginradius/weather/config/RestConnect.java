package com.loginradius.weather.config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.loginradius.weather.exception.ConfigAPIException;

/*
 * Class for connecting to REST API
 */
@Component
public class RestConnect {
	
	private static final Logger logger = Logger.getLogger(RestConnect.class);
	
	@Autowired
	RestTemplateFactory restFactory;
			
	public RestTemplate restTemplate;
	public String apiPassword;
	public String apiUserName;
	public String ipAddress;
	public ResponseEntity<String> response;
	public HttpEntity<String> request;
	public String errorMessage;
	

	public ResponseEntity<String> get(String url) throws ConfigAPIException {
		restTemplate = initRestTemplate();
		request = restFactory.getHttpEntity("JSON",null);//no need to pass xmlentity

		if(restTemplate !=null && request !=null) {
			logger.info("url="+url);
			try {
				response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
			}catch (HttpClientErrorException e) {
				ConfigAPIException configAPIException = new ConfigAPIException(e.getStatusCode(),e.getStatusText(),e.getResponseBodyAsString());
				logger.info(e);
				logger.info("Error - " + e.getResponseBodyAsString());
				if(e.getStatusCode().value() == 404)
					errorMessage = "404 - Resource Not Found";
				else if(e.getStatusCode().value() == 403) {
					errorMessage = "Error 403 - Forbidden, Resource not available";
				}
				else if(e.getStatusCode().value() == 401) {
					errorMessage = "Error 401 - Unauthorized";
				}
				else 
					errorMessage = JAXBUtilities.parseJsonForAPIError(e.getResponseBodyAsString());
				configAPIException.setErrorMessage(errorMessage);
				throw configAPIException;
			}
			catch (ResourceAccessException e) {
				ConfigAPIException configAPIException = null;
				configAPIException = new ConfigAPIException(e.getMessage());
				if( e.getCause().getClass().getSimpleName().equals("HttpHostConnectException")) {
					errorMessage = "Host Connect Exception. Cannot connect to AW";
				} else {
					try {
						errorMessage = JAXBUtilities.xmlToJson(e.getMessage());
					} catch (IOException e1) {
						e1.printStackTrace();
						errorMessage = "XmlParseException Input/Output Exception";
					}
				}
				configAPIException.setErrorMessage(errorMessage);
				throw configAPIException;
			}
			catch (HttpServerErrorException e) {
				ConfigAPIException configAPIException = new ConfigAPIException(e.getStatusCode(),e.getStatusText());
				try {
					errorMessage = JAXBUtilities.xmlToJson(e.getStatusText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				configAPIException.setErrorMessage(errorMessage);
				throw configAPIException;
			}
			catch (RestClientException e) {
				ConfigAPIException configAPIException = new ConfigAPIException(e.getMessage());
				try {
					errorMessage = JAXBUtilities.xmlToJson(e.getMessage());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				configAPIException.setErrorMessage(errorMessage);
				throw configAPIException;
			} 
		}
		return response;
	}
	
	/*
	 * Initialize RestTemplate
	 */
	public RestTemplate initRestTemplate (){
		try {
			this.restTemplate = this.restFactory.getRestTemplate();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.restTemplate;
	}
		
}