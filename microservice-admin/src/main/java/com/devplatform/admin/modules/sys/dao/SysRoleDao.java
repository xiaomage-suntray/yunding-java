package com.devplatform.admin.modules.sys.dao;

import java.util.List;

import com.devplatform.admin.modules.sys.bean.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;


import com.devplatform.admin.modules.sys.bean.SysRoleEntity;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * 角色管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:33:33
 */
@Mapper
public interface SysRoleDao extends MyBaseMapper<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<String> queryRoleIdList(String createUserId);
	/**
	 * TODO 查询当前登陆人的角色列表  id  name  供应商评分角色使用
	 *
	 * @return List<SysRoleEntity>
	 * @Param  sysUserRoleEntity
	 * @Author heibin
	 * @Date 2019/4/9 13:49
	 **/
	List<SysRoleEntity> sysRoleList(SysUserRoleEntity sysUserRoleEntity);
}
