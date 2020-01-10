package com.devplatform.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ListUtil {
	
	/**
	 * List深度拷贝
	 * @param src
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException List
	 * @throws
	 * @author Rice
	 * @date 2018年7月19日
	 */
	public static List deepCopy(List src) throws IOException, ClassNotFoundException{
		  ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		  ObjectOutputStream out = new ObjectOutputStream(byteOut);
		  out.writeObject(src);

		  ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		  ObjectInputStream in =new ObjectInputStream(byteIn);
		  List dest = (List)in.readObject();
		  return dest;
	}
	

}
