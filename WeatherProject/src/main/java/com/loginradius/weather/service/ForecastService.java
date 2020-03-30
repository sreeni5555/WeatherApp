package com.loginradius.weather.service;

import com.loginradius.weather.exception.CityNotFoundException;
import com.loginradius.weather.model.ForecastData;

public interface ForecastService {

	ForecastData getForecast(String data) throws CityNotFoundException, Exception;
}
