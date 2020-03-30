package com.loginradius.weather.config;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


/**
 * @author Sreeni
 * @description Used for conversion xml to json, create xml entities
 */
@Component
public class JAXBUtilities{
	private static final Logger logger = Logger.getLogger(JAXBUtilities.class);

	/*
	 * create xml body for Object.  Used for POST
	 */
	public String createXMLBodyForObject(Object object, Class<? extends Object> objectClassName) {
		String result = null;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(objectClassName);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter stringWriter = new StringWriter();
			jaxbMarshaller.marshal(object,stringWriter );

			result = stringWriter.toString();
			logger.info("XML Body : "+result);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return result;

	}
	/*
	 * Create xml for sendign data to API. Used for generic objects
	 */
	public String createGenericXMLBodyForObject(Object object) {
		JacksonXmlModule xmlModule = new JacksonXmlModule();
		xmlModule.setDefaultUseWrapper(false);

		ObjectMapper objectMapper = new XmlMapper(xmlModule);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String xml =null;
		try {
			xml = objectMapper.writeValueAsString(object);
			logger.info(xml);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml;
	}
	
	/*
	 * Converts xml from API response to JSON
	 */
	public static String xmlToJson(String xml) throws JsonParseException, JsonMappingException, IOException{
		 ObjectMapper mapper = new ObjectMapper(); 
		 XmlMapper xmlMapper = new XmlMapper();
		 xmlMapper.setConfig(xmlMapper.getSerializationConfig().withRootName(""));
		 Map<?, ?> map = xmlMapper.readValue(xml, Map.class);
		 String result =null;
		 
		 String json = mapper.writeValueAsString(map);
		 logger.info("xmltojson error:"+json);
		 logger.info(json);
		 CustomJSONParser parser = new CustomJSONParser();
		 result = parser.parseJSONForAPIErrors(json) ;
		 
		 return result ;
		 }

	/* 
	 * parse json for any api error
	 */
	public static String parseJsonForAPIError(String json) {
		CustomJSONParser parser = new CustomJSONParser();
		String result = "";//parser.parseJSONForAPIErrors(json) ;
		 return result;
	}
	
}
	
