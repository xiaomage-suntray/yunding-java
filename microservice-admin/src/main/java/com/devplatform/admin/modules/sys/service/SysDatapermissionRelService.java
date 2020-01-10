package com.devplatform.admin.modules.sys.service;

import com.devplatform.common.service.MyBaseService;

import java.util.List;

import com.devplatform.admin.modules.sys.bean.SysDatapermissionRel;

/**
 * 数据权限关系表的service接口
 * <br>
 * <b>功能：</b>SysDatapermissionRelService<br>
 * @author 代码生成器产生
 */
public interface SysDatapermissionRelService extends MyBaseService<SysDatapermissionRel> {

	void saveOrUpdatePostRel(String postId, List<String> dataPermissionIdList);

	void saveOrUpdateOrgRel(String orgId, List<String> dataPermissionIdList);

}
