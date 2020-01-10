package com.devplatform.admin.modules.sys.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysUserPost;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * SysUserPost Mapper
 * 用于用户岗位关系的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysUserPostDao extends MyBaseMapper<SysUserPost> {
	
	
}
