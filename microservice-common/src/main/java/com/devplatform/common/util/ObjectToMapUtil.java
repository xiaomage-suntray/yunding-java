package com.devplatform.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


public class ObjectToMapUtil {

	public static Map getParamMap(HttpServletRequest request) {
		Map<String,String[]> parameters=request.getParameterMap();
		Set<String> set=parameters.keySet();
		Iterator<String> it=set.iterator();
		Map<String, Object> data=new HashMap<String, Object>();
		while(it.hasNext()) {
			String key=it.next();
			String[] values=parameters.get(key);
			if(values.length>0&&values[0]!=null) {
				data.put(key, values[0]);
			}
		}
		return data;
	}
}
