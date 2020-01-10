package com.devplatform.admin.modules.generation.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.devplatform.common.util.MathUtil;

public class ValidateUtil {
	
	public final static boolean isDouble(Object key) {
		try {
			if (key instanceof java.lang.Double) {
				return true;
			}
			if (isEmpty(key)) {
				return true;
			}
			if (key instanceof java.lang.String) {
				Double.parseDouble(key.toString());
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public final static boolean isInt(Object key) {
		try {
			if (key instanceof java.lang.Integer) {
				return true;
			}
			if (isEmpty(key)) {
				return true;
			}
			if (key instanceof java.lang.String) {
				Integer.parseInt(key.toString());
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public final static boolean isLong(Object key) {
		try {
			if (key instanceof java.lang.Long) {
				return true;
			}
			if (isEmpty(key)) {
				return true;
			}
			if (key instanceof java.lang.String) {
				Long.parseLong(key.toString());
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public final static boolean isNumber(Object key) {
		return isInt(key) || isDouble(key) || isLong(key);
	}


	/**
	 * 验证手机号格式，以1开头，第二位为3～8的11位字符串
	 * 
	 * @param mobile
	 * @return
	 */
	public final static boolean isMobile(String mobile) {
		try {
			if (mobile.length() != 11) {
				return false;
			}
			if (!("1").equals(String.valueOf(mobile.charAt(0)))) {
				return false;
			}
			if (MathUtil.getInt(mobile.substring(1, 2)) > 9 || MathUtil.getInt(mobile.substring(1, 2)) <= 2) {
				return false;
			}
			return isNumber(mobile);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	/**
	 * 验证身份证格式
	 * 
	 * @param mobile
	 * @return
	 */
	public final static boolean isIdCard(String value) {
		try {
			String[] scc_idarray = {"7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2"};
			String[] check_idarray = {"1","0","'X'","9","8","7","6","5","4","3","2"};

			boolean re=true;
			if(!(value.length()==18||value.length()==15)){
				return false;
			}
			if(value.length()==15){
				for(int i=0;i<value.length();i++){
					//TODO 
				}
				return re;
			}else{
				String[] val = value.split("");
				int  d = 0 ;
				for(int i=0 ; i<17 ;i++){
					d += Integer.parseInt(val[i]) * Integer.parseInt(scc_idarray[i]);
				}
				int b = d % 11 ; 
				if(val[17].equals(check_idarray[b])){
					re = false;
				}
				return re;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * 验证邮箱格式
	 * 
	 * @param mobile
	 * @return
	 */
	public final static boolean isEmail(String email) {
		try {
			if (email.indexOf("@") == -1) {
				return false;
			}
			if (email.indexOf(".") == -1) {
				return false;
			}
			if (email.indexOf(".") < email.indexOf("@")) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	/**
	 * 验证长度(字符串为null或者“”返回false，或者长度值大于等于length 返回true) 否则返回false
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public final static boolean isLonger(String str, int length) {
		if (isEmpty(str)) {
			return false;
		}
		return str.length() >= length;
	}

	/**
	 * 验证长度(字符串为null或者“”返回false，或者长度值大于等于start 返回true) 否则返回false
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public final static boolean isShorter(String str, int length) {
		if (isEmpty(str)) {
			return false;
		}
		return str.length() <= length;
	}
	/**
	 * 验证数字大小(字符串为null或者“”返回false ,int1 比int2 大 返回true) 否则返回false
	 * 
	 * @param int1
	 * @param int2
	 * @return
	 */
	public final static boolean isGreeter(Object int1,Object int2) {
		
		if(int1.getClass() != int2.getClass()){
			return false;
		}
		
		if(int1 instanceof Integer && int2 instanceof Integer){
			return Integer.parseInt(int1.toString()) > Integer.parseInt(int2.toString());
		}
		if(int1 instanceof Long && int2 instanceof Long){
			return Long.parseLong(int1.toString()) > Long.parseLong(int2.toString());
		}
		if(int1 instanceof Double && int2 instanceof Double){
			return Double.parseDouble(int1.toString()) > Double.parseDouble(int2.toString());
		}
		return false;
	}
	
	public final static boolean isBetween(String str, int start, int end) {
		return isLonger(str, start) && isShorter(str, end);
	}

	public final static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		if ("".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public final static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof Object[]) {
			if (((Object[]) obj).length == 0) {
				return true;
			} else {
				return false;
			}
		}

		if (obj instanceof List<?>) {
			if (((List<?>) obj).size() == 0) {
				return true;
			} else {
				return false;
			}
		}

		if (obj instanceof Set<?>) {
			if (((Set<?>) obj).size() == 0) {
				return true;
			} else {
				return false;
			}
		}

		if (obj instanceof Map<?, ?>) {
			if (((Map<?, ?>) obj).size() == 0) {
				return true;
			} else {
				return false;
			}
		}

		if ("".equals(String.valueOf(obj).trim())) {
			return true;
		}
		return false;
	}

	public final static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public final static boolean isNotBetween(String str, int start, int end) {
		if (isEmpty(str)) {
			return false;
		}
		return str.length() > end || str.length() < start;
	}

	public final static boolean isPwd(String str, String regexp) {
		return match(str, regexp);
	}

	public final static boolean isPwd(String str) {
		return match(str, "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_!@#$%^&*`~()-+=]+$)(?![0-9\\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_!@#$%^&*`~()-+=]{8,32}$");
	}
	
	public final static boolean isPwdEight(String str) {
		return match(str, "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8}$");
	}

	private final static boolean match(String text, String reg) {
		if (isEmpty(text) || isEmpty(reg))
			return false;
		return Pattern.compile(reg).matcher(text).matches();
	}

	public final static boolean isSame(String source, String target) {
		if (source == null && target == null) {
			return true;
		}
		if (source == null) {
			return false;
		}
		if (target == null) {
			return false;
		}
		return source.equals(target);
	}

}
