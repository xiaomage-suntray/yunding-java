package com.${bean.pacageName}.service.impl;

import javax.annotation.Resource;
import com.${bean.pacageName}.page.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.${bean.pacageName}.service.${bean.classOracleName}Service;
import com.${bean.pacageName}.dao.${bean.classOracleName}Dao;
import com.${bean.pacageName}.bean.${bean.classOracleName};
/**
 * ${bean.tableRemark}的业务实现类
 * 功能：${bean.classOracleName}ServiceImpl
 * 表名：${bean.tableName}
 * @author 代码生成器产生
 */
@Service("${bean.lowerOracleName}Service")
public class ${bean.classOracleName}ServiceImpl implements ${bean.classOracleName}Service  {
	protected final static Logger log= Logger.getLogger(${bean.classOracleName}ServiceImpl.class);

	@Resource
	private ${bean.classOracleName}Dao dao;

		
	public ${bean.classOracleName}Dao getDao() {
		return dao;
	}
	/**
	 * 列表查询,带分页
	 * @param ${bean.classOracleName} ,Page
	 * @return Page
	 */
	@Override
	public Page pageQuery(${bean.classOracleName} bean,Page page) {
		PageHelper.startPage(page.getPageNum() <= 0 ? 1 : page.getPageNum(), page.getPageSize());
		List<${bean.classOracleName}> list = dao.getList(bean);
		PageInfo<${bean.classOracleName}> p = new PageInfo<${bean.classOracleName}>(list);
		page.setResult(list);
		page.setTotal(p.getTotal());
		return page;
	}
	/**
	 * 列表查询
	 * @param ${bean.classOracleName}
	 * @return List<${bean.classOracleName}>
	 */
	@Override
	public List<${bean.classOracleName}> getList(${bean.classOracleName} bean) {
		return dao.getList(bean);
	}
	 /**
     * 添加方法
     * @param ${bean.classOracleName}
     * @return Integer
     */
	@Override
	public Integer save(${bean.classOracleName} bean) {
		Integer count = dao.save(bean);
		return count;
	}
	/**
     * 修改方法
     * @param ${bean.classOracleName}
     * @return Integer
     */
	@Override
	public Integer update(${bean.classOracleName} bean) {
		 Integer count = dao.update(bean);
		 return count;
	}
	/**
     * 根据id获取单条数据
     * @param id
     * @return ${bean.classOracleName}
     */
	@Override
	public ${bean.classOracleName} getById(String id) {
		return dao.getById(id);
	}
	 /**
     * 获取单条数据
     * @param ids
     * @return Integer
     */
	@Override
	public Integer delete(String[] ids) {
		Integer count = 0 ;
		if(ids!=null && !ids.equals("")){
			for(String id : ids){
				count += dao.delete(id);
			}
		}
		return count;
	}
	
	
	

}
