package com.loginradius.weather.config;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import com.loginradius.weather.model.ForecastData;

@Component
public class CustomJSONParser {
	private static final Logger logger = Logger.getLogger(CustomJSONParser.class);
	
	/*  
	 * Parse JSON object from Weather API and return the object that needs to be displayed in the UI
	 */	
	@SuppressWarnings("rawtypes")
	public ForecastData parseJSON(String jsonString) {
		JSONParser jsonParser = (JSONParser) new JSONParser();
		JSONObject json;

		ForecastData forecastData = null;
		try {
			json = (JSONObject)jsonParser.parse(jsonString);
			logger.info("json for ForecastData: "+json );

			//if json is empty or invalid because the city entered is an invalid one, return empty
			if(json==null||json.get("error")!=null)
				return null;
			
			if(json!=null) {
				forecastData = new ForecastData();
				JSONObject locationObj = (JSONObject)json.get("location");
				logger.info("locationObj:"+locationObj);
	
				if(locationObj!=null) {
					if(locationObj.get("name")!=null) 
						forecastData.setName(locationObj.get("name").toString());
					if(locationObj.get("country")!=null) 
						forecastData.setCountry(locationObj.get("country").toString());
					if(locationObj.get("region")!=null) 
						forecastData.setRegion(locationObj.get("region").toString());
					if(locationObj.get("lat")!=null) 
						forecastData.setLat(locationObj.get("lat").toString());
					if(locationObj.get("lon")!=null) 
						forecastData.setLon(locationObj.get("lon").toString());
					if(locationObj.get("timezone_id")!=null) 
						forecastData.setTimezone(locationObj.get("timezone_id").toString());
				}
	
				JSONObject currentObj = (JSONObject)json.get("current");
				logger.info("currentObj:"+currentObj);
	
				if(currentObj!=null) {
					if(currentObj.get("temperature")!=null) 
						forecastData.setTemperature(currentObj.get("temperature").toString());
					if(currentObj.get("wind_speed")!=null) 
						forecastData.setWindSpeed(currentObj.get("wind_speed").toString());
					if(currentObj.get("wind_degree")!=null) 
						forecastData.setWindDegree(currentObj.get("wind_degree").toString());
					if(currentObj.get("wind_dir")!=null) 
						forecastData.setWindDir(currentObj.get("wind_dir").toString());
					if(currentObj.get("pressure")!=null) 
						forecastData.setPressure(currentObj.get("pressure").toString());
					if(currentObj.get("precip")!=null) 
						forecastData.setPrecip(currentObj.get("precip").toString());
					if(currentObj.get("humidity")!=null) 
						forecastData.setHumidity(currentObj.get("humidity").toString());
					if(currentObj.get("cloudcover")!=null) 
						forecastData.setCloudCover(currentObj.get("cloudcover").toString());
					if(currentObj.get("feelslike")!=null) 
						forecastData.setFeelsLike(currentObj.get("feelslike").toString());
					if(currentObj.get("uv_index")!=null) 
						forecastData.setUvIndex(currentObj.get("uv_index").toString());
					if(currentObj.get("visibility")!=null) 
						forecastData.setVisibility(currentObj.get("visibility").toString());
					
					/* since weather descriptions is array, take each and put in comma delimited list */
					if(currentObj.get("weather_descriptions")!=null) {					
						JSONArray descArray = (JSONArray)currentObj.get("weather_descriptions");
						String descr="";
						if(descArray!=null) {
							for (Iterator iterator = descArray.iterator(); iterator.hasNext();) {
								Object obj = iterator.next();
								String eachSubString = null;								
								
								if(obj instanceof String) {
									 eachSubString = (String) obj;
								}else if (obj instanceof JSONObject) {
									JSONObject temp = (JSONObject) obj;
									eachSubString = temp.toString();
								}
								if(eachSubString!=null) {
								    if(descr.length()>0)
								    	descr = descr+","+eachSubString;
								    else
								    	descr = eachSubString;
								}
							}
						}
						forecastData.setWeatherDescription(descr);
					}				
				}
			}
			
			return forecastData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * parse the error and throw the right error
	 */
	public String parseJSONForAPIErrors(String response) {
		logger.info("Exec parseJSONForAPIErrors()");
		if(response!=null) {
			JSONParser jsonParser = (JSONParser) new JSONParser();
			JSONObject json ,apiError ;
			json = apiError=null;
			JSONArray apiErrorArray =null;
			String errorType = null;
			String errorData = null;
			String errorMesg= null;
			String resultErrorMsg = "";
			String delimiter = "\n";
			try {
				json = (JSONObject)jsonParser.parse(response);
				if(json!=null) {
					logger.info("json : "+json );
					Object apiErrorObj= json .get("apiError");
					if (apiErrorObj!=null && apiErrorObj instanceof JSONArray) {
						apiErrorArray = (JSONArray)apiErrorObj;
						for(int i= 0 ;i<apiErrorArray.size();i++) {
							JSONObject apiErrorJson = (JSONObject )apiErrorArray.get(i);
							errorType = apiErrorJson.get("errorType")!=null ? apiErrorJson.get("errorType").toString():"";
							errorData = apiErrorJson.get("errorData")!=null ? apiErrorJson.get("errorData").toString():"";
							errorMesg = apiErrorJson.get("errorMessage")!=null ? apiErrorJson.get("errorMessage").toString():"";
							
							resultErrorMsg = resultErrorMsg .concat(errorType).concat(delimiter).concat(errorMesg).concat(delimiter).concat(errorData).concat(delimiter);
							logger.info("resultErrorMsg  : "+resultErrorMsg );
						}
						return resultErrorMsg;
					}
					else if (apiErrorObj!=null && apiErrorObj instanceof JSONObject) {
						apiError = (JSONObject)apiErrorObj;
						errorType = apiError.get("errorType")!=null ? apiError.get("errorType").toString():"";
						errorData = apiError.get("errorData")!=null ? apiError.get("errorData").toString():"";
						errorMesg = apiError.get("errorMessage")!=null ? apiError.get("errorMessage").toString():"";

						resultErrorMsg = resultErrorMsg .concat(errorType).concat(delimiter) .concat(errorMesg).concat(delimiter).concat(errorData).concat(delimiter);
						logger.info("resultErrorMsg  : "+resultErrorMsg );
						return resultErrorMsg;
					}else {
						errorMesg = (String)json.get("Code");
						resultErrorMsg = resultErrorMsg .concat(errorMesg).concat(delimiter);
					}
				}
				else
					logger.info("JSON Error is Null : ");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return resultErrorMsg ;
		}else {
			logger.info("ResponseEntity is NULL !!!");
			return null;
		}
	}


}