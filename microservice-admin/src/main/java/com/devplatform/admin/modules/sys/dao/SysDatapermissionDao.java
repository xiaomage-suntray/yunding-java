package com.devplatform.admin.modules.sys.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysDatapermission;
import com.devplatform.common.dao.MyBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysDatapermission Mapper
 * 用于数据权限表的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysDatapermissionDao extends MyBaseMapper<SysDatapermission> {
	
	public List<SysDatapermission> queryListByUserId(@Param("userId") String userId);
}
