package com.loginradius.weather.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.loginradius.weather.config.Messages;
import com.loginradius.weather.exception.CityNotFoundException;
import com.loginradius.weather.model.ForecastData;
import com.loginradius.weather.service.ForecastService;

import javax.servlet.http.HttpServletRequest;

/**
 * Web controller to process all web actions
 *
 * @author Sri
 */
@Controller
@RequestMapping("/")
public class ForecastController {

	private static final Logger logger = Logger.getLogger(ForecastController.class);

	@Autowired
	ForecastService forecastService;	

	@Autowired
	Messages messages;

	 @RequestMapping("/")
     public String home(ModelMap model) {
        return "forecast";
     } 

	  @RequestMapping(value = { "/GetForecast" }, method = RequestMethod.POST)
	  public String getData(ModelMap model,@RequestParam String city, HttpServletRequest request) {
		  logger.info("getData()------variable city:"+city+":");
		  
		  ForecastData cityData =  null;
		  
		  try {
			  if(city.isEmpty())
				  throw new CityNotFoundException(messages.get("city.empty"));
			  
			  cityData = forecastService.getForecast(city);
			  
			  if(cityData!=null)
				  model.addAttribute("cityData", cityData);
			  else
				  model.addAttribute("errorMesg", messages.get("city.invalid"));
		  
		  }catch(CityNotFoundException cnfe) {
			  logger.info("CityNotFoundException:"+cnfe);
			  model.addAttribute("errorMesg",cnfe.getMessage());
		  }
		  catch(Exception e) {
			  logger.info("exception:"+e);
			  model.addAttribute("errorMesg",e.getMessage());			  
		  }
		  		  
		  return "forecast";
	  }
}
