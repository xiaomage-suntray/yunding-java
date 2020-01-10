package com.devplatform.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具类
 *
 */
public class SerializeUtil {
	public static Object read(InputStream input){
		Object o = null;
		ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(input);
			o=ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ois!=null)
				try {
					ois.close();
				} catch (IOException e) {
				}
		}
		return o;
	}
	public static Object read(byte[] data){
		Object o = null;
		ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(new ByteArrayInputStream(data));
			o=ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ois!=null)
				try {
					ois.close();
				} catch (IOException e) {
				}
		}
		return o;
	}
	public static void write(String path,Object o){
		ObjectOutputStream oos=null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(o);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(oos!=null)
				try {
					oos.close();
				} catch (IOException e) {
				}
		}
	}
	public static byte[] write(Object o){
		ObjectOutputStream oos=null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(oos!=null)
				try {
					oos.close();
				} catch (IOException e) {
				}
		}
		return baos.toByteArray();
	}
	
}
