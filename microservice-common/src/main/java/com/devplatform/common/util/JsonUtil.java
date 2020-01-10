package com.devplatform.common.util;


import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 *
 */
public class JsonUtil {
	private ObjectMapper objectMapper;

	public JsonUtil() {
		objectMapper = new ObjectMapper();
	}

	public String toJson(Object bean) {
		String value = null;
		StringWriter out = null;
		try {
			out = new StringWriter();
			objectMapper.writeValue(out, bean);
			value = out.toString();
		} catch (Exception e) {
			throw new RuntimeException();
		}finally {
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
				}
		}
		return value;
	}
	
	public Object fromJson(String json,Class<?> clazz) {
		Object value = null;
		try {
			value = objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return value;
	}
}
