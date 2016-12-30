package gxp.app.util;


import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

import gxp.app.entity.GxpEntity;

public class JsonParser {
	
	public static GxpEntity parseJsonDataToObject(String jsonPayLoad){
		
		ObjectMapper mapper = new ObjectMapper();
		try{
			return mapper.readValue(jsonPayLoad, GxpEntity.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static GxpEntity parseJsonStringToPojo(String jsonPayLoad){
		GxpEntity gxpEntity = new GxpEntity();
		try {
			// read the json file
//			FileReader reader = new FileReader(filePath);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonPayLoad);
			JSONArray lang= (JSONArray) jsonObject.get("xpasses");			
			Iterator i = lang.iterator();	
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				gxpEntity.setSwid(innerObj.get("swid").toString());
				gxpEntity.setIdentifierValue(innerObj.get("id").toString());
				gxpEntity.setGxpLinkId(innerObj.get("gxp-link-id").toString());
			}		

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return gxpEntity;
	}

}
