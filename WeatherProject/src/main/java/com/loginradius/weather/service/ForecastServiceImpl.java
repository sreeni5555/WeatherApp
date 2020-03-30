package com.loginradius.weather.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.loginradius.weather.config.CustomJSONParser;
import com.loginradius.weather.config.RestConnect;
import com.loginradius.weather.exception.ConfigAPIException;
import com.loginradius.weather.model.ForecastData;
import com.loginradius.weather.model.RequestLog;
import com.loginradius.weather.repository.RequestLogRepository;


@Service("forecastService")
public class ForecastServiceImpl  implements ForecastService {
	private static final Logger logger = Logger.getLogger(ForecastServiceImpl.class);
	private static final String appUrl="WeatherAppURL";
	private static final String accessKey="access_key";
	private static final String appKey="WeatherAppKey";
	
	Boolean status = false;	

	@Autowired
	private RestConnect restConnect;	
	
	@Autowired
	Environment env;
	
	  @Autowired
	  private RequestLogRepository requestLogRepository;

	@Override
	public	ForecastData getForecast(String city) throws Exception {
		logger.error("this is from logger");

		ForecastData data = new ForecastData();		
		RequestLog requestLogInfo = new RequestLog(city);
		
		requestLogRepository.save(requestLogInfo);
		data = getAPIResponse(city);
		
		logger.info("response:"+data);
		
	 return data;
	}

	
	public ForecastData getAPIResponse(String city) throws ConfigAPIException,Exception {
		ForecastData forecastData = null;
		try {
			String url = env.getProperty(appUrl) + "?"+accessKey+"=" + env.getProperty(appKey)+"&query="+city;
			logger.info("url -- " + url);
			ResponseEntity<String> response = restConnect.get(url);
			logger.debug("response:"+response);
			forecastData = new CustomJSONParser().parseJSON(response.getBody());
		}catch(Exception e) {
			throw new ConfigAPIException(e.getMessage());
		}
						
		return forecastData;
	}

}
