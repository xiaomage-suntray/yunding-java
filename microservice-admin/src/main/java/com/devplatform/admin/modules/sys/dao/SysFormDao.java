package com.devplatform.admin.modules.sys.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysForm;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * SysForm Mapper
 * 用于系统表单的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysFormDao extends MyBaseMapper<SysForm> {
	
	
}
