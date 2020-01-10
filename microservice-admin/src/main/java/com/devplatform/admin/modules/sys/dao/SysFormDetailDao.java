package com.devplatform.admin.modules.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysFormDetail;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * SysFormDetail Mapper
 * 用于系统表单明细的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysFormDetailDao extends MyBaseMapper<SysFormDetail> {

	/**
	 * 根据ID获取系统表单明细
	 * @param id 表单对象主键
	 * @return List<SysFormDetail>
	 * @author songdx
	 */
	List<SysFormDetail> getChildrenById(String formId);

	/**
	 * 根据表单URL模糊查询获取系统表单明细
	 * @param url 表单地址
	 * @return
	 */
	List<SysFormDetail> getChildrenByUrlLike(String url);
	
	
}
