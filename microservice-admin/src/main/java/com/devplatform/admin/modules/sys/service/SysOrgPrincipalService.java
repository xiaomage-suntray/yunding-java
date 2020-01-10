package com.devplatform.admin.modules.sys.service;

import com.devplatform.common.service.MyBaseService;

import com.devplatform.admin.modules.sys.bean.SysOrgPrincipal;

import java.util.List;

/**
 * 组织机构与负责人关联关系表的service接口
 * <br>
 * <b>功能：</b>SysOrgPrincipalService<br>
 * @author 代码生成器产生
 */
public interface SysOrgPrincipalService extends MyBaseService<SysOrgPrincipal> {

    void saveOrUpdateOrgRel(String orgId, List<String> userIdList, String createUserId);

}
