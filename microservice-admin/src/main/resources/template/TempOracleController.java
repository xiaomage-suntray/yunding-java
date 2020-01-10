package com.${bean.pacageName}.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cnpay.page.Page;
import com.${bean.pacageName}.bean.${bean.classOracleName};
import com.${bean.pacageName}.service.${bean.classOracleName}Service;
import com.cnpay.response.ResponseCode;
import com.cnpay.common.controller.BaseAction;

 
@Controller
@RequestMapping("/${bean.lowerOracleName}") 
public class ${bean.classOracleName}Controller extends BaseAction{
	
	protected final static Logger log= Logger.getLogger(${bean.classOracleName}Controller.class);
	
	
	@Autowired(required=false)
	private ${bean.classOracleName}Service ${bean.lowerOracleName}Service; 
	
	/**
	 * 列表页面列表数据获取
	 * @param model 承接对象
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	@ResponseBody
	public Page datalist(${bean.classOracleName} bean,Page page) throws Exception{
		page = ${bean.lowerOracleName}Service.pageQuery(bean,page);
	    return page;
	}
	/**
	 * 添加或修改数据
	 * @param bean 要操作的对象
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(${bean.classOracleName} bean) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		if(bean.getId() == null){
			 ${bean.lowerOracleName}Service.save(bean);
		}else{
			 ${bean.lowerOracleName}Service.update(bean);
		}
		result.put("code", ResponseCode.OK);
		result.put("msg", ResponseCode.codeMap.get(ResponseCode.OK));
	    return result;
	}
	/**
	 * 根据ID获取对象
	 * @param id 对象主键
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getId")
	@ResponseBody
	public Map<String, Object> getId(String id) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		${bean.classOracleName} bean = ${bean.lowerOracleName}Service.getById(id);
		if(bean == null){
			result.put("code", ResponseCode.PARAM_INVALID);
			result.put("msg", ResponseCode.codeMap.get(ResponseCode.PARAM_INVALID));
			return result;
		}
		result.put("code", ResponseCode.OK);
		result.put("msg", ResponseCode.codeMap.get(ResponseCode.OK));
		result.put("data", bean);
		return result;
	}
	/**
	 * 根据ID获取对象
	 * @param id[] 对象主键数组
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String[] id) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Integer count = ${bean.lowerOracleName}Service.delete(id);
		if(count>0){
		  result.put("code", ResponseCode.OK);
		  result.put("msg", ResponseCode.codeMap.get(ResponseCode.OK));
	    }
		return result;
	}

	
}
