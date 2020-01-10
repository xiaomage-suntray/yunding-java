package com.devplatform.admin.modules.sys.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysDatapermissionRel;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * SysDatapermissionRel Mapper
 * 用于数据权限关系表的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysDatapermissionRelDao extends MyBaseMapper<SysDatapermissionRel> {
	
	
}
