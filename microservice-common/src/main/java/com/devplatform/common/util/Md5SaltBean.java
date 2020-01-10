package com.devplatform.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * md5+salt盐加密验证
 *
 */
public class Md5SaltBean {

    private static final String HEX_NUMS_STR="0123456789ABCDEF";   
    private static final Integer SALT_LENGTH = 12;   
    private static final String ALGORITHM = "MD5";   
    private static final String ENCODING = "UTF-8";   
       
    private String salt;
    private String password;
    
    
    public String getSalt() {
		return salt;
	}

	public String getPassword() {
		return password;
	}

	/**   
     * 将16进制字符串转换成字节数组   
     * @param hex   
     * @return   
     */  
    public static byte[] hexStringToByte(String hex) {   
        int len = (hex.length() / 2);   
        byte[] result = new byte[len];   
        char[] hexChars = hex.toCharArray();   
        for (int i = 0; i < len; i++) {   
            int pos = i * 2;   
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4    
                            | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));   
        }   
        return result;   
    }   
       
    /**  
     * 将指定byte数组转换成16进制字符串  
     * @param b  
     * @return  
     */  
    public static String byteToHexString(byte[] b) {   
        StringBuffer hexString = new StringBuffer();   
        for (int i = 0; i < b.length; i++) {   
            String hex = Integer.toHexString(b[i] & 0xFF);   
            if (hex.length() == 1) {   
                hex = '0' + hex;   
            }   
            hexString.append(hex.toUpperCase());   
        }   
        return hexString.toString();   
    }   
       
    /**  
     * 验证口令是否合法  
     * @param password  
     * @param passwordInDb  
     * @return  
     * @throws NoSuchAlgorithmException  
     * @throws UnsupportedEncodingException  
     */  
    public boolean valid(String password,String encryptd, String salt)   
            throws NoSuchAlgorithmException, UnsupportedEncodingException {   
        //将16进制字符串格式口令转换成字节数组   
        byte[] pwdInDb = hexStringToByte(encryptd);   
        //创建消息摘要对象   
        MessageDigest md = MessageDigest.getInstance(ALGORITHM);   
        //将盐数据传入消息摘要对象   
        md.update(hexStringToByte(salt));   
        //将口令的数据传给消息摘要对象   
        md.update(password.getBytes(ENCODING));   
        //生成输入口令的消息摘要   
        byte[] digest = md.digest();   
        //比较根据输入口令生成的消息摘要和数据库中消息摘要是否相同   
        if (Arrays.equals(digest, pwdInDb)) {   
            //口令正确返回口令匹配消息   
            return true;   
        } else {   
            //口令不正确返回口令不匹配消息   
            return false;   
        }   
    }   
  
    /**  
     * 获得加密后的16进制形式口令  
     * @param password  
     * @return  
     * @throws NoSuchAlgorithmException  
     * @throws UnsupportedEncodingException  
     */  
    public void encrypt(String password)   
            throws NoSuchAlgorithmException, UnsupportedEncodingException {   
        //声明加密后的口令数组变量   
        //随机数生成器   
        SecureRandom random = new SecureRandom();   
        //声明盐数组变量   12
        byte[] salt = new byte[SALT_LENGTH];   
        //将随机数放入盐变量中   
        random.nextBytes(salt);   
        this.salt=byteToHexString(salt);
        //声明消息摘要对象   
        MessageDigest md = null;   
        //创建消息摘要   
        md = MessageDigest.getInstance(ALGORITHM);   
        //将盐数据传入消息摘要对象   
        md.update(salt);   
        //将口令的数据传给消息摘要对象   
        md.update(password.getBytes(ENCODING));   
        //获得消息摘要的字节数组   
        byte[] digest = md.digest();   
  
        //将字节数组格式加密后的口令转化为16进制字符串格式的口令   
        this.password=byteToHexString(digest);   
    }  
}