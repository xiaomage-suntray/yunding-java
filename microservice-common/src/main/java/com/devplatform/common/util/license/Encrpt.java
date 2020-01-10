package com.devplatform.common.util.license;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


public class Encrpt {
	private static final String KEY_MD5 = "MD5";
	// 全局数组
	private static final String[] strDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "-", "|", "@", "&", "a", "b", "c", "d", "e", "f",
		"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
		"u", "v", "w", "x", "y", "z" };

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}
	/**
	 * MD5加密
	 * @param strObj
	 * @return
	 * @throws Exception
	 */
	public static String GetMD5Code(String strObj) throws Exception{
		MessageDigest md = MessageDigest.getInstance(KEY_MD5);
		// md.digest() 该函数返回值为存放哈希值结果的byte数组
		return byteToString(md.digest(strObj.getBytes()));
	}


	//rsa
	private static Certificate getCertificate(InputStream cerStream) {

		try {
			//返回指定证书类型的 CertificateFactory 对象。X.509是由国际电信联盟（ITU-T）制定的数字证书标准。
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//			FileInputStream in = new FileInputStream(certificatePath);
			Certificate certificate = certificateFactory.generateCertificate(cerStream);
			cerStream.close();
			return certificate;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static final int MAX_ENCRYPT_BLOCK = 117;
	//java自带keytool生成的工具密钥长度为2048，所以解密块长度为2048/8=256 同理若密钥长度为1024，解密块长度为128
	private static final int MAX_DECRYPT_BLOCK=256;
	//base64转换
	public static BASE64Encoder encode=new BASE64Encoder();
	public static BASE64Decoder decoder = new BASE64Decoder();
	public static String EncriptWRSA_Pub(String data,InputStream cerStream) throws Exception{
		String encryptData ="";
		X509Certificate x509Certificate = (X509Certificate) getCertificate(cerStream);
		// 获得公钥
		PublicKey publicKey = x509Certificate.getPublicKey();
		//公钥加密
		Cipher cipher = Cipher.getInstance("rsa");
		SecureRandom random = new SecureRandom();
		cipher.init(Cipher.ENCRYPT_MODE, publicKey, random);

		try {
			//  Cipher cipher = Cipher.getInstance("RSA");
			//   cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			int length = data.getBytes().length;
			int offset = 0;
			byte[] cache;
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			int i = 0;
			while(length - offset > 0){
				if(length - offset > MAX_ENCRYPT_BLOCK){
					cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
				}else{
					cache = cipher.doFinal(data.getBytes(), offset, length - offset);
				}
				outStream.write(cache, 0, cache.length);
				i++;
				offset = i * MAX_ENCRYPT_BLOCK;
			}
			return encode.encode(outStream.toByteArray());//encodeBase64(outStream.toByteArray());
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptData;
	}
	public static String EncriptWRSA_Pri(String data,String path) throws Exception{
		String encryptData ="";
		try {
			FileInputStream in = new FileInputStream(path);
			KeyStore ks = KeyStore.getInstance("JKS");// JKS: Java KeyStoreJKS，可以有多种类型
			ks.load(in, "123456".toCharArray());
			in.close();

			String alias = "suntray"; // 记录的别名
			String pswd = "123456"; // 记录的访问密码
			//获取私钥
			PrivateKey privateKey = (PrivateKey) ks.getKey(alias, pswd.toCharArray());
			//私钥加密
			Cipher cipher = Cipher.getInstance("rsa");
			SecureRandom random = new SecureRandom();
			cipher.init(Cipher.ENCRYPT_MODE, privateKey, random);


			//  Cipher cipher = Cipher.getInstance("RSA");
			//   cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			int length = data.getBytes().length;
			int offset = 0;
			byte[] cache;
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			int i = 0;
			while(length - offset > 0){
				if(length - offset > MAX_ENCRYPT_BLOCK){
					cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
				}else{
					cache = cipher.doFinal(data.getBytes(), offset, length - offset);
				}
				outStream.write(cache, 0, cache.length);
				i++;
				offset = i * MAX_ENCRYPT_BLOCK;
			}
			return encode.encode(outStream.toByteArray());
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptData;
	}
	public static String DecriptWithRSA_Pub(String data,InputStream cerStream) throws Exception{
		X509Certificate x509Certificate = (X509Certificate) getCertificate(cerStream);
		// 获得公钥
		PublicKey publicKey = x509Certificate.getPublicKey();

		Cipher cipher = Cipher.getInstance("rsa");
		SecureRandom random = new SecureRandom();

		byte[] bEncrypt = decoder.decodeBuffer(data);
		//私钥解密
		cipher.init(Cipher.DECRYPT_MODE, publicKey, random);
		int inputLen = bEncrypt.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(bEncrypt, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(bEncrypt, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return  new String(decryptedData);
	}
	public static String DecriptWithRSA_Pri(String data,String path) throws Exception{
		FileInputStream in = new FileInputStream(path);
		KeyStore ks = KeyStore.getInstance("JKS");// JKS: Java KeyStoreJKS，可以有多种类型
		ks.load(in, "suntray".toCharArray());
		in.close();

		String alias = "suntray"; // 记录的别名
		String pswd = "123456*"; // 记录的访问密码
		//获取私钥
		PrivateKey privateKey = (PrivateKey) ks.getKey(alias, pswd.toCharArray());

		Cipher cipher = Cipher.getInstance("rsa");
		SecureRandom random = new SecureRandom();

		byte[] bEncrypt = decoder.decodeBuffer(data);
		//私钥解密
		cipher.init(Cipher.DECRYPT_MODE, privateKey, random);
		// byte[] plainData = cipher.doFinal(bEncrypt);
		//  System.out.println("11111:"+new String(plainData));
		int inputLen = bEncrypt.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(bEncrypt, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(bEncrypt, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return  new String(decryptedData);
	}

}
