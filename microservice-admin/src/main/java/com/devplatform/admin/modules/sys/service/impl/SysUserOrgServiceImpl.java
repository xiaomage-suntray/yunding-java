package com.devplatform.admin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devplatform.admin.modules.sys.bean.SysUserOrg;
import com.devplatform.admin.modules.sys.dao.SysUserOrgDao;
import com.devplatform.admin.modules.sys.service.SysUserOrgService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;
import com.devplatform.common.util.MapUtils;

/**
 * 用户组织机构关系的service接口实现类
 * <br>
 * <b>功能：</b>SysUserOrgServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysUserOrgService")
public class SysUserOrgServiceImpl extends MyBaseServiceImpl<SysUserOrgDao, SysUserOrg> implements SysUserOrgService {

	@Override
	public void saveOrUpdate(String userId, List<String> orgIdList) {
		//先删除用户与岗位关系
		this.removeByMap(new MapUtils().put("user_id", userId));

		if(orgIdList == null || orgIdList.size() == 0){
			return ;
		}

		//保存用户与岗位关系
		List<SysUserOrg> list = new ArrayList<>(orgIdList.size());
		for(String orgIdAndCode : orgIdList){
			SysUserOrg sysUserOrg = new SysUserOrg();
			String [] tmpArray = orgIdAndCode.split("#");
			String orgId = "";
			String orgCode = "";
			if(tmpArray.length > 0){
				orgId = tmpArray[0];
			}
			if(tmpArray.length == 2){
				orgCode = tmpArray[1];
			}
			sysUserOrg.setUserId(userId);
			sysUserOrg.setOrgId(orgId);
			sysUserOrg.setOrgCode(orgCode);
			list.add(sysUserOrg);
		}
		this.saveBatch(list);
	}

}
