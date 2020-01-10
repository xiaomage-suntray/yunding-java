package com.devplatform.admin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devplatform.admin.modules.generation.constant.DATAPERMISSSION_ENUM;
import com.devplatform.admin.modules.sys.bean.SysDatapermissionRel;
import com.devplatform.admin.modules.sys.dao.SysDatapermissionRelDao;
import com.devplatform.admin.modules.sys.service.SysDatapermissionRelService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;
import com.devplatform.common.util.MapUtils;

/**
 * 数据权限关系表的service接口实现类
 * <br>
 * <b>功能：</b>SysDatapermissionRelServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysDatapermissionRelService")
public class SysDatapermissionRelServiceImpl extends MyBaseServiceImpl<SysDatapermissionRelDao, SysDatapermissionRel> implements SysDatapermissionRelService {

	@Override
	public void saveOrUpdatePostRel(String postId, List<String> dataPermissionIdList) {
		//先删除岗位与数据权限关系
		this.removeByMap(new MapUtils().put("post_id", postId));

		if(dataPermissionIdList == null || dataPermissionIdList.size() == 0){
			return ;
		}

		//保存岗位与数据权限关系
		List<SysDatapermissionRel> list = new ArrayList<>(dataPermissionIdList.size());
		for(String dataPermissionId : dataPermissionIdList){
			SysDatapermissionRel sysDatapermissionRel = new SysDatapermissionRel();
			sysDatapermissionRel.setPostId(postId);
			sysDatapermissionRel.setType(DATAPERMISSSION_ENUM.POST.getType());
			sysDatapermissionRel.setDatapermissionId(dataPermissionId);
			list.add(sysDatapermissionRel);
		}
		this.saveBatch(list);
	}

	@Override
	public void saveOrUpdateOrgRel(String orgId, List<String> dataPermissionIdList) {
			//先删除组织机构与数据权限关系
			this.removeByMap(new MapUtils().put("org_id", orgId));

			if(dataPermissionIdList == null || dataPermissionIdList.size() == 0){
				return ;
			}

			//保存组织机构与数据权限关系
			List<SysDatapermissionRel> list = new ArrayList<>(dataPermissionIdList.size());
			for(String dataPermissionId : dataPermissionIdList){
				SysDatapermissionRel sysDatapermissionRel = new SysDatapermissionRel();
				sysDatapermissionRel.setOrgId(orgId);
				sysDatapermissionRel.setType(DATAPERMISSSION_ENUM.ORG.getType());
				sysDatapermissionRel.setDatapermissionId(dataPermissionId);
				list.add(sysDatapermissionRel);
			}
			this.saveBatch(list);
	}

}
