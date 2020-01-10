package com.cnpay.service;

import java.util.List;
import com.cnpay.bean.${bean.classOracleName};
import com.cnpay.page.Page;

/**
 * ${bean.tableRemark}的业务接口
 * 功能：${bean.classOracleName}Service
 * 表名：${bean.tableName}
 * @author 代码生成器产生
 */
public interface ${bean.classOracleName}Service  {
	/**
	 * 列表查询,带分页
	 * @param ${bean.classOracleName} ,Page
	 * @return Page
	 */
	public Page pageQuery(${bean.classOracleName} bean,Page page);
	/**
	 * 列表查询
	 * @param ${bean.classOracleName}
	 * @return List<${bean.classOracleName}>
	 */
	public List<${bean.classOracleName}> getList(${bean.classOracleName} bean);
	 /**
     * 添加方法
     * @param ${bean.classOracleName}
     * @return Integer
     */
	public Integer save(${bean.classOracleName} bean);
	/**
     * 修改方法
     * @param ${bean.classOracleName}
     * @return Integer
     */
	public Integer update(${bean.classOracleName} bean);
	 /**
     * 根据id获取单条数据
     * @param id
     * @return ${bean.classOracleName}
     */
	public ${bean.classOracleName} getById(String id);
	 /**
     * 获取单条数据
     * @param id
     * @return Integer
     */
	public Integer delete(String[] id);
    
}
