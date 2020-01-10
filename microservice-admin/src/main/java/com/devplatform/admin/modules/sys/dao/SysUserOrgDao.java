package com.devplatform.admin.modules.sys.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysUserOrg;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * SysUserOrg Mapper
 * 用于用户组织机构关系的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysUserOrgDao extends MyBaseMapper<SysUserOrg> {
	
	
}
