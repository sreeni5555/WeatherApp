package com.loginradius.weather.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ForecastData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String country;
	private String region;
	private String lat;
	private String lon;
	private String timezone;	
	private String temperature;
	private String weatherIcon;
	private String weatherDescription;
	private String windSpeed;
	private String windDegree;
	private String windDir;
	private String pressure;
	private String precip;
	private String humidity;
	private String cloudCover;
	private String feelsLike;
	private String uvIndex;
	private String visibility;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWeatherIcon() {
		return weatherIcon;
	}
	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}
	public String getWeatherDescription() {
		return weatherDescription;
	}
	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}
	public String getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getWindDegree() {
		return windDegree;
	}
	public void setWindDegree(String windDegree) {
		this.windDegree = windDegree;
	}
	public String getWindDir() {
		return windDir;
	}
	public void setWindDir(String windDir) {
		this.windDir = windDir;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getPrecip() {
		return precip;
	}
	public void setPrecip(String precip) {
		this.precip = precip;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getCloudCover() {
		return cloudCover;
	}
	public void setCloudCover(String cloudCover) {
		this.cloudCover = cloudCover;
	}
	public String getFeelsLike() {
		return feelsLike;
	}
	public void setFeelsLike(String feelsLike) {
		this.feelsLike = feelsLike;
	}
	public String getUvIndex() {
		return uvIndex;
	}
	public void setUvIndex(String uvIndex) {
		this.uvIndex = uvIndex;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
