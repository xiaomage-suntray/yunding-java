package com.devplatform.admin.modules.generation.util;


import java.util.HashMap;
import java.util.Map;

public class GeneratorConstant {
	
	
	private GeneratorConstant() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static final String FGF="/";
	
	public static final String IS_NULLABLE = "IS_NULLABLE";
	public static final String ENUMAT = "ENUMAT";
	public static final String COLUMN_NAME = "COLUMN_NAME";
	public static final String COLUMN_COMMENT = "COLUMN_COMMENT";
	public static final String DATA_TYPE = "DATA_TYPE";
	public static final String IS_EMAIL = "IS_EMAIL";
	public static final String IS_IDNO = "IS_IDNO";
	public static final String IS_MOBILE = "IS_MOBILE";
	public static final String MAX_LENGTH = "MAX_LENGTH";
	public static final String MIN_LENGTH = "MIN_LENGTH";
	public static final String REGEX = "REGEX";
	
	public static final String SUCCESS = "success";
	public static final String MSG = "msg";
	public static final String DATA = "data";


	public static final String TYPE_STRING = "java.lang.String";
	public static final String TYPE_BIGDECIMAL = "java.math.BigDecimal";
	public static final String TYPE_BOOLEAN = "Boolean";
	public static final String TYPE_DOUBLE = "Double";
	public static final String TYPE_BYTEARRAY = "Byte[]";
	public static final String TYPE_DATE = "java.util.Date";
	public static final String TYPE_BYTE = "Byte";
	public static final String TYPE_SHORT = "Short";
	public static final String TYPE_INTEGER = "Integer";
	public static final String TYPE_LONG = "Long";
	public static final String TYPE_FLOAT = "Float";

	public static Map<String, String> getMap() {
		Map<String,String> typeMap = new HashMap<>();
		typeMap.put("CHAR",TYPE_STRING);
		typeMap.put("VARCHAR",TYPE_STRING);
		typeMap.put("LONGVARCHAR",TYPE_STRING);
		typeMap.put("LONGTEXT",TYPE_STRING);
		typeMap.put("TEXT",TYPE_STRING);
		typeMap.put("NUMERIC",TYPE_BIGDECIMAL);
		typeMap.put("DECIMAL",TYPE_BIGDECIMAL);
		typeMap.put("BIT",TYPE_BOOLEAN);
		typeMap.put("BOOLEAN",TYPE_BOOLEAN);
		typeMap.put("TINYINT",TYPE_BYTE);
		typeMap.put("SMALLINT",TYPE_SHORT);
		typeMap.put("INTEGER",TYPE_INTEGER);
		typeMap.put("INT",TYPE_INTEGER);
		typeMap.put("BIGINT",TYPE_LONG);
		typeMap.put("REAL",TYPE_FLOAT);
		typeMap.put("FLOAT",TYPE_DOUBLE);
		typeMap.put("DOUBLE",TYPE_DOUBLE);
		typeMap.put("NUMBER",TYPE_DOUBLE);
		typeMap.put("BINARY",TYPE_BYTEARRAY);
		typeMap.put("VARBINARY",TYPE_BYTEARRAY);
		typeMap.put("LONGVARBINARY",TYPE_BYTEARRAY);
		typeMap.put("DATE",TYPE_DATE);
		typeMap.put("DATETIME",TYPE_DATE);
		typeMap.put("TIME",TYPE_DATE);
		typeMap.put("TIMESTAMP",TYPE_DATE);
		typeMap.put("CLOB",TYPE_BYTEARRAY);
		typeMap.put("BLOB",TYPE_BYTEARRAY);
		return typeMap;
	}

}
