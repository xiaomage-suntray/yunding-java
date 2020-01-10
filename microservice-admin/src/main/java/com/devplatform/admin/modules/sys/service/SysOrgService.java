package com.devplatform.admin.modules.sys.service;

import com.devplatform.admin.modules.sys.bean.SysOrg;
import com.devplatform.common.service.MyBaseService;

/**
 * 组织机构管理的service接口
 * <br>
 * <b>功能：</b>SysOrgService<br>
 * @author 代码生成器产生
 */
public interface SysOrgService extends MyBaseService<SysOrg> {
	
	/**
	 * 保存组织机构
	 * @return 
	 */
	public boolean save(SysOrg sysPost);
	
	
	/**
	 * 修改组织机构
	 * @return 
	 */
	public boolean update(SysOrg sysPost);
	
}
