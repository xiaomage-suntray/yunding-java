package com.devplatform.common.util;

public class MathUtil {

	public static double max(double... num) {
		if (num == null || num.length == 0)
			return 0;
		double max = num[0];
		for (double n : num) {
			if (n > max) {
				max = n;
			}
		}
		return max;
	}

	public double min(double... num) {
		if (num == null || num.length == 0)
			return 0;
		double min = num[0];
		for (double n : num) {
			if (n < min) {
				min = n;
			}
		}
		return min;
	}
	
	public static double getDouble(String key){
		try {
			return Double.valueOf(key);
		} catch (Exception e) {
			return 0;
		}
	}
	public static int getInt(String key){
		try {
			return Integer.valueOf(key);
		} catch (Exception e) {
			return 0;
		}
	}

	public static boolean isDouble(String key){
		try {
			Double.parseDouble(key);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isInt(String key){
		try {
			Integer.parseInt(key);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
