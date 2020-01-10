package com.devplatform.admin.modules.generation.util;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {

	private DBConnectionUtil() {
	}

	public static Connection getMysqlConnection(String url, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			long begin, end;
			begin = System.currentTimeMillis();
			System.out.print("开始获取连接：	");
			Connection conn = DriverManager.getConnection(url, user, password);// 获取连接
			end = System.currentTimeMillis();
			System.out.println("获取连接耗时：	" + (end - begin) + "毫秒");
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("没有找到数据库驱动",e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("数据库连接异常",e);
		}
	}
	public static Connection getOracleConnection(String url, String user, String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			long begin, end;
			begin = System.currentTimeMillis();
			System.out.print("开始获取连接：	");
			Connection conn = DriverManager.getConnection(url, user, password);// 获取连接
			end = System.currentTimeMillis();
			System.out.println("获取连接耗时：	" + (end - begin) + "毫秒");
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("没有找到数据库驱动",e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("数据库连接异常",e);
		}
	}

}
