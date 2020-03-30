package com.loginradius.weather.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateFactory {

	private static final Logger logger = Logger.getLogger(RestTemplateFactory.class);


	public RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {		
		//Ignore SSL
		SSLContextBuilder sslcontext = new SSLContextBuilder();
		sslcontext.loadTrustMaterial(null, new TrustSelfSignedStrategy()); 
		CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslcontext.build()).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
		      .build();

	    HttpComponentsClientHttpRequestFactory requestFactory =
	                    new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient);
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
		
	    return restTemplate;
	}

	/*
	 * Creating Http Entity to be sent to request API
	 */
	public HttpEntity<String> getHttpEntity (String mediaType,String xmlBody){				
		HttpEntity<String> httpEntity=null;
		String base64Creds = "No Auth";
		HttpHeaders headers = new HttpHeaders();
		if(mediaType.equalsIgnoreCase("GET_XML")) {
			headers.add("Authorization", "Basic " + base64Creds);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
			httpEntity = new HttpEntity<String>(headers);
		}
		else if(mediaType.equalsIgnoreCase("XML")) {
			headers.add("Authorization", "Basic " + base64Creds);
			headers.setContentType(MediaType.APPLICATION_XML);
			httpEntity = new HttpEntity<String>(xmlBody,headers);
		}
		else {
			headers.add("Authorization", "Basic " + base64Creds);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			httpEntity = new HttpEntity<String>(headers);
		}
		return httpEntity;
	}

}
