package com.devplatform.admin.modules.sys.service;

import com.devplatform.common.service.MyBaseService;

import java.util.List;

import com.devplatform.admin.modules.sys.bean.SysUserPost;

/**
 * 用户岗位关系的service接口
 * <br>
 * <b>功能：</b>SysUserPostService<br>
 * @author 代码生成器产生
 */
public interface SysUserPostService extends MyBaseService<SysUserPost> {

	void saveOrUpdate(String userId, List<String> postIdList);

}
