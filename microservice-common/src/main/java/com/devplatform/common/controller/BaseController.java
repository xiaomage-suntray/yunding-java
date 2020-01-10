package com.devplatform.common.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.devplatform.common.util.DateTimeUtil;
import com.devplatform.common.util.StringUtil;

/**
 * @Title: 腾信微服务快速开发平台
 * @Description: Controller基础类
 * @Copyright: Copyright (c) 2018
 * @Company: 北京腾信软创科技股份有限公司
 * @author Rice
 * @version 1.1  2018年11月8日
 */

public class BaseController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpSession session;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpSession getSession() {
		return session;
	}
	
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		dataBinder.setAutoGrowCollectionLimit(2046);
		dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport(this) {
			
			@Override
			public String getAsText() {
				try {
					return DateTimeUtil.parse2String((Date) getValue(), "yyyy-MM-dd HH:mm:ss");
				}
				catch (Exception e) {
				}
				return DateTimeUtil.parse2String((Date) getValue(), "yyyy-MM-dd");
			}
			
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (StringUtil.isValid(text))
					return;
				if (StringUtils.split(text, " ").length > 1)
					setValue(DateTimeUtil.parse2Date(text, "yyyy-MM-dd HH:mm:ss"));
				else
					setValue(DateTimeUtil.parse2Date(text, "yyyy-MM-dd"));
			}
			
		});
		dataBinder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport(this) {
			
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (StringUtil.isValid(text))
					return;
				setValue(new Timestamp(DateTimeUtil.parse2Date(text, "yyyy-MM-dd HH:mm:ss").getTime()));
			}
			
			@Override
			public String getAsText() {
				return DateTimeUtil.parse2String((Date) getValue(), "yyyy-MM-dd HH:mm:ss");
			}
		});
	}
	
	/**
	 * 所有ActionMap 统一从这里获取
	 * 
	 * @return
	 */
	public Map<String, Object> getRootMap() {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		return rootMap;
	}
	
	public ModelAndView forward(String viewName, Map<String, Object> context) {
		return new ModelAndView(viewName, context);
	}
	
	public ModelAndView error(String errMsg) {
		return new ModelAndView("error");
	}
	
}
