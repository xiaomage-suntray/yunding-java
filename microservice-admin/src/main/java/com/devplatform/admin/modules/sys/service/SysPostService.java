package com.devplatform.admin.modules.sys.service;

import com.devplatform.admin.modules.sys.bean.SysPost;
import com.devplatform.common.service.MyBaseService;

/**
 * 岗位管理的service接口
 * <br>
 * <b>功能：</b>SysPostService<br>
 * @author 代码生成器产生
 */
public interface SysPostService extends MyBaseService<SysPost> {
	
	/**
	 * 保存岗位
	 * @return 
	 */
	boolean save(SysPost sysPost);
	
	
	/**
	 * 修改岗位
	 * @return 
	 */
	boolean update(SysPost sysPost);
}
