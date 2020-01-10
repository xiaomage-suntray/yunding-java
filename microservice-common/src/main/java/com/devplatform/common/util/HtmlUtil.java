package com.devplatform.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b><br>
 * <b>日期：</b> 2013-03-01 <br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class HtmlUtil {

	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出json格式<br>
	 * <b>作者：</b><br>
	 * <b>日期：</b>2013-4-17<br>
	 * 
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	static JsonUtil json = new JsonUtil();
	
	public static void writerJson(HttpServletResponse response, String jsonStr) {
		writer(response, jsonStr);
	}
	public static void writerJsonhtrss(HttpServletResponse response,String nr,String url) {
		 StringBuffer buffer=new StringBuffer();
		 buffer.append("<html>");
		 buffer.append("<head>");
		 buffer.append("<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'>");
		 buffer.append("</head>");
		 buffer.append("<body onload='loaddd()'>");
		 buffer.append("<script type=\"text/javascript\" src=\"../js/frontAlertaction.js\"></script>"); 
		 buffer.append("<script type='text/javascript'>function loaddd(){alert('"+nr+"');setTimeout('adddd()',3000);}function adddd(){location.href='"+url+"'}</script>");
		 buffer.append("</body></html>"); 
		 writerJsonht(response, buffer.toString());
	}
	
	public static void writerJsonht(HttpServletResponse response, String jsonStr) {
		writerht(response, jsonStr);
	}

	public static void writerJson(HttpServletResponse response, Object object) {
		try {
			response.setContentType("text/html");
			System.out.println(json.toJson(object));
			writer(response, json.toJson(object));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出HTML代码<br>
	 * <b>作者：</b><br>
	 * <b>日期：</b>2013-4-17<br>
	 * 
	 * @param response
	 * @param htmlStr
	 * @throws Exception
	 */
	public static void writerHtml(HttpServletResponse response, String htmlStr) {
		writer(response, htmlStr);
	}

	private static void writer(HttpServletResponse response, String str) {
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writerht(HttpServletResponse response, String str) {
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出HTML代码<br>
	 * <b>作者：</b><br>
	 * <b>日期：</b>2013-4-17<br>
	 * 
	 * @param response
	 * @param htmlStr
	 * @throws Exception
	 */
	public static void writerHtml(HttpServletResponse response, String htmlStr,
			String encode) {
		writer(response, htmlStr, encode);
	}

	private static void writer(HttpServletResponse response, String str,
			String encode) {
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding(encode);
			PrintWriter out = null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
