package com.devplatform.admin.modules.sys.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysOrgPrincipal;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * SysOrgPrincipal Mapper
 * 用于组织机构与负责人关联关系表的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysOrgPrincipalDao extends MyBaseMapper<SysOrgPrincipal> {
	
	
}
