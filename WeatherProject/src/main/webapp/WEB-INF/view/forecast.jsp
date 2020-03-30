<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
   response.setHeader("Pragma","no-cache"); //HTTP 1.0 
   response.setDateHeader ("Expires", -1); //prevents caching
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Weather forecast</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<META HTTP-EQUIV="Expires" CONTENT="-1">

<style> 
        table { 
            border-collapse: collapse; 
            width: 100%; 
        } 
          
        th, td { 
            text-align: left; 
            padding: 8px; 
        } 
          
        tr:nth-child(even) { 
            background-color: Lightgreen; 
        } 
    </style> 
</head>
<body style="margin:30">
		<h2>Get Weather Forecast for the any city</h2>		
		<form style="wdith:400;align:center" action="/GetForecast" method="post">
				<div style="height:25">
					Enter the city and click on 'Get Forecast'
				</div>
				<div style="width:500:align:center">
					&nbsp;&nbsp;
					<label class="form-label">City:</label>
					<input type="text" id="city" name="city" placeholder="enter city here">&nbsp;								
					<button type="submit" style="margin:0;">Get Forecast</button>
				</div>
		</form>
							
			<c:if test="${not empty errorMesg}">
			  <div style="color:red">${errorMesg}</div>
			</c:if>				
			
			<c:if test="${not empty cityData}">
			<table style="text-align:left;width:600;align:center">
				<tr>
					<th style="width:250">Description Type</th>
					<th style="width:350">City Info</th>
				</tr>
				<tr>
					<td>City Name:</td>
					<td>${cityData.name}</td>
				</tr>
				<tr>
					<td>Country:</td>
					<td>${cityData.country}</td>
				</tr>
				<tr>
					<td>Region:</td>
					<td>${cityData.region}</td>
				</tr>
				<tr>
					<td>Latitude:</td>
					<td>${cityData.lat}</td>
				</tr>
				<tr>
					<td>Longitude:</td>
					<td>${cityData.lon}</td>
				</tr>
				<tr>
					<td>Timezone:</td>
					<td>${cityData.timezone}</td>
				</tr>
				<tr>
					<td>Weather Description:</td>
					<td>${cityData.weatherDescription}</td>
				</tr>
				<tr>
					<td>Temperature:</td>
					<td>${cityData.temperature}</td>
				</tr>
				<tr>
					<td>Wind Speed:</td>
					<td>${cityData.windSpeed}</td>
				</tr>
				<tr>
					<td>Wind Degree:</td>
					<td>${cityData.windDegree}</td>
				</tr>
				<tr>
					<td>Wind Direction:</td>
					<td>${cityData.windDir}</td>
				</tr>
				<tr>
					<td>Pressure:</td>
					<td>${cityData.pressure}</td>
				</tr>
				<tr>
					<td>Precipitation:</td>
					<td>${cityData.precip}</td>
				</tr>
				<tr>
					<td>Humidity:</td>
					<td>${cityData.humidity}</td>
				</tr>		
				<tr>
					<td>Feels like:</td>
					<td>${cityData.feelsLike}</td>
				</tr>
				<tr>
					<td>UV Index:</td>
					<td>${cityData.uvIndex}</td>
				</tr>
				<tr>
					<td>Visibility:</td>
					<td>${cityData.visibility}</td>
				</tr>
			</table>		
			</c:if>
			    
</body>
</html>