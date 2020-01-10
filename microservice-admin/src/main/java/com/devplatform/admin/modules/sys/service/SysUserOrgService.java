package com.devplatform.admin.modules.sys.service;

import com.devplatform.common.service.MyBaseService;

import java.util.List;

import com.devplatform.admin.modules.sys.bean.SysUserOrg;

/**
 * 用户组织机构关系的service接口
 * <br>
 * <b>功能：</b>SysUserOrgService<br>
 * @author 代码生成器产生
 */
public interface SysUserOrgService extends MyBaseService<SysUserOrg> {

	void saveOrUpdate(String userId, List<String> orgIdList);

}
