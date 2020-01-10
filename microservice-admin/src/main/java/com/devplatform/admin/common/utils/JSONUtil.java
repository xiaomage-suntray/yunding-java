/**
 * Copyright(c) 2010-2013 by XiangShang Inc.
 * All Rights Reserved
 */

package com.devplatform.admin.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonReader;

/**
 * json util
 * 
 * @author jinkai
 */
public class JSONUtil {
    
    /**
     * bean to json
     * 
     * @see file without date type
     */
    public static String beanToJson(Object obj, boolean serializeNullValue) {
        
        if (obj == null) {
            return null;
        }
        // Bean -> Json
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue
                ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG)
                        .create()
                : gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
        String json = gson.toJson(obj);
        return json;
    }
    
    /**
     * beantoMap
     * 
     * @param obj
     * @return
     */
    public static Map<String, Object> beantoMap(Object obj) {
        
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    
                    map.put(key, value);
                }
                
            }
        }
        catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        
        return map;
        
    }
    
    /**
     * bean to json 适用于毫秒
     * 
     * @see with date type
     */
    public static String beanWithDateToJson(Object obj, boolean serializeNullValue) {
        
        if (obj == null) {
            return null;
        }
        // date serializable
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue
                ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG)
                        .create()
                : gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
        // Bean -> Json
        String json = gson.toJson(obj);
        return json;
    }
    
    /**
     * json to bean
     * 
     * @see file without date type
     */
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        StringReader strReader = new StringReader(json);
        JsonReader jsonReader = new JsonReader(strReader);
        return jsonToBean(jsonReader, clazz);
    }
    
    private static <T> T jsonToBean(JsonReader json, Class<T> clazz) {
        
        if (json == null) {
            return null;
        }
        // Json -> Bean
        T bean = jsonWithDateToBean(json, clazz);
        return bean;
    }
    
    /**
     * json to bean 适用于毫秒
     * 
     * @see with date type
     */
    public static <T> T jsonWithDateToBean(String json, Class<T> clazz) {
        
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        StringReader strReader = new StringReader(json);
        JsonReader jsonReader = new JsonReader(strReader);
        return jsonWithDateToBean(jsonReader, clazz);
    }
    
    private static <T> T jsonWithDateToBean(JsonReader json, Class<T> clazz) {
        
        if (json == null) {
            return null;
        }
        // date deserializable
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateDeserializerUtils()).setDateFormat(DateFormat.LONG).create();
        // Json -> Bean
        T b = gson.fromJson(json, clazz);
        return b;
    }
    
    /**
     * list to json
     * 
     * @see without date type
     */
    public static <T> String listToJson(List<T> list, boolean serializeNullValue) {
        
        if (list == null) {
            return null;
        }
        // List -> Json
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue
                ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG)
                        .create()
                : gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
        String json = gson.toJson(list);
        return json;
    }
    
    /**
     * list to json
     * 
     * @seee with date type
     */
    public static <T> String listWithDateToJson(List<T> list, boolean serializeNullValue) {
        
        if (list == null) {
            return null;
        }
        // date serializable
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue
                ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG)
                        .create()
                : gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
        // List -> Json
        String json = gson.toJson(list);
        return json;
    }
    
    /**
     * json to list
     * 
     * @see without date type
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        // json -> List
        StringReader strReader = new StringReader(json);
        List<T> list = null;
        try {
            list = readForList(strReader, false, clazz);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * json to list
     * 
     * @see with date type
     */
    public static <T> List<T> jsonWithDateToList(String json, Class<T> clazz) {
        
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        
        // json -> List
        StringReader strReader = new StringReader(json);
        List<T> list = null;
        try {
            list = readForList(strReader, true, clazz);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private static <T> List<T> readForList(Reader reader, boolean hasDate, Class<T> clazz) throws IOException {
        
        JsonReader jsonReader = new JsonReader(reader);
        List<T> objs = new ArrayList<T>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            T obj = null;
            if (hasDate) {
                obj = jsonWithDateToBean(jsonReader, clazz);
            }
            else {
                obj = jsonToBean(jsonReader, clazz);
            }
            if (obj != null)
                objs.add(obj);
        }
        jsonReader.endArray();
        jsonReader.close();
        return objs;
    }
    
    /**
     * set to json
     * 
     * @see without date type
     */
    public static <T> String setToJson(Set<T> set, boolean serializeNullValue) {
        
        if (set == null) {
            return null;
        }
        // set -> Json
        Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls().create() : new Gson();
        String json = gson.toJson(set);
        return json;
    }
    
    /**
     * set to json
     * 
     * @seee with date type
     */
    public static <T> String setWithDateToJson(Set<T> set, boolean serializeNullValue) {
        
        if (set == null) {
            return null;
        }
        // date serializable
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue
                ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG)
                        .create()
                : gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
        // List -> Json
        String json = gson.toJson(set);
        return json;
    }
    
    /**
     * json to set
     * 
     * @see without date type
     */
    public static <T> Set<T> jsonToSet(String json, Class<T> clazz) {
        
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        // json -> set
        StringReader strReader = new StringReader(json);
        Set<T> set = null;
        try {
            set = readForSet(strReader, false, clazz);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }
    
    /**
     * json to set
     * 
     * @see with date type
     */
    public static <T> Set<T> jsonWithDateToSet(String json, Class<T> clazz) {
        
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        
        // json -> set
        StringReader strReader = new StringReader(json);
        Set<T> set = null;
        try {
            set = readForSet(strReader, true, clazz);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }
    
    private static <T> Set<T> readForSet(Reader reader, boolean hasDate, Class<T> clazz) throws IOException {
        
        JsonReader jsonReader = new JsonReader(reader);
        Set<T> objs = new HashSet<T>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            T obj = null;
            if (hasDate) {
                obj = jsonWithDateToBean(jsonReader, clazz);
            }
            else {
                obj = jsonToBean(jsonReader, clazz);
            }
            if (obj != null)
                objs.add(obj);
        }
        jsonReader.endArray();
        jsonReader.close();
        return objs;
    }
    
    /**
     * map to json
     * 
     * @see whithout date type
     */
    public static <T> String mapToJson(Map<String, T> map, boolean serializeNullValue) {
        
        if (map == null) {
            return null;
        }
        // Map -> Json
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue
                ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG)
                        .create()
                : gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
        String json = gson.toJson(map);
        return json;
    }
    
    /**
     * map to json
     * 
     * @see with date type
     */
    public static <T> String mapWithDateToJson(Map<String, T> map, boolean serializeNullValue) {
        
        if (map == null) {
            return null;
        }
        // date serializable
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue
                ? gsonBuilder.serializeNulls().registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG)
                        .create()
                : gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
        // map -> json
        String json = gson.toJson(map);
        return json;
    }
    
    /**
     * 日期解序列实用工具类
     */
    static class DateSerializerUtils implements JsonSerializer<java.util.Date> {
        
        @Override
        public JsonElement serialize(Date date, Type type, JsonSerializationContext content) {
            
            return new JsonPrimitive(date.getTime());
        }
        
    }
    
    /**
     * 日期序列化实用工具类
     */
    static class DateDeserializerUtils implements JsonDeserializer<java.util.Date> {
        
        @Override
        public java.util.Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            
            return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
        }
    }
    
    /**
     * 根据json字符串返回Map对象
     * 
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json, Class<?> clas) {
        
        return JSONUtil.toMap(JSONUtil.toJsonObject(json, null), clas);
    }
    
    /**
     * 将JSONObjec对象转换成Map-List集合
     * 
     * @param json
     * @return
     */
    private static Map<String, Object> toMap(JsonObject json, Class<?> clas) {
        
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext();) {
            Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof JsonArray)
                map.put(key, toList(entry.getValue().getAsJsonArray(), clas));
            else if (value instanceof JsonObject)
                map.put(key, jsonToBean(entry.getValue().toString(), clas));
            else {
                if (value.equals("null") || value == null || value == "" || value instanceof JsonNull) {
                    map.put(key, null);
                }
                else {
                    map.put(key, entry.getValue().getAsString());
                }
            }
        }
        return map;
    }
    
    /**
     * 将JSONArray对象转换成List集合
     * 
     * @param json
     * @return
     */
    public static List<?> toList(JsonArray json, Class<?> clas) {
        
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < json.size(); i++) {
            Object value = json.get(i);
            if (value instanceof JsonObject) {
                list.add(jsonToBean(value.toString(), clas));
            }
            else {
                list.add(value);
            }
        }
        return list;
    }
    
    public static Gson createGson() {
        
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = builder.create();
        return gson;
    }
    
    /**
     * 获取JsonObject
     * 
     * @param json
     * @return
     */
    public static JsonObject toJsonObject(String json, String jsonObject) {
        
        JsonParser parser = new JsonParser();
        if (StringUtils.hasText(jsonObject)) {
            JsonObject jsonObj = (JsonObject) parser.parse(json).getAsJsonObject().get("responseBody");
            return jsonObj;
        }
        else {
            JsonObject jsonObj = parser.parse(json).getAsJsonObject();
            return jsonObj;
        }
    }
    
}
