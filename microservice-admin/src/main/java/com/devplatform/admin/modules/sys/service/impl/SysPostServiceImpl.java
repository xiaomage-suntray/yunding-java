package com.devplatform.admin.modules.sys.service.impl;

import java.util.Date;

import com.devplatform.admin.modules.sys.bean.SysSerialNumber;
import com.devplatform.admin.modules.sys.service.SysSerialNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devplatform.admin.modules.sys.bean.SysPost;
import com.devplatform.admin.modules.sys.dao.SysPostDao;
import com.devplatform.admin.modules.sys.service.SysDatapermissionRelService;
import com.devplatform.admin.modules.sys.service.SysPostService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

/**
 * 岗位管理的service接口实现类
 * <br>
 * <b>功能：</b>SysPostServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysPostService")
public class SysPostServiceImpl extends MyBaseServiceImpl<SysPostDao, SysPost> implements SysPostService {
	
	@Autowired
	private SysDatapermissionRelService sysDatapermissionRelService;

	@Autowired
	private SysSerialNumberService sysSerialNumberService;

	@Override
	@Transactional
	public boolean save(SysPost sysPost) {
		sysPost.setCreateTime(new Date());
		//查询父节点的code.
		SysPost tmpParentNode = this.getById(sysPost.getParentId());
		String postCode = "";
		SysSerialNumber tmpSerialNumber = new SysSerialNumber();
		sysSerialNumberService.insertForAutoIncr(tmpSerialNumber);
		String serialNumberStr = String.format("%04d",tmpSerialNumber.getSerialNumber());
		if(null != tmpParentNode && null != tmpParentNode.getPostCode() && !"".equals(tmpParentNode.getPostCode())){
			postCode = tmpParentNode.getPostCode() + "_" + serialNumberStr;
		}else{
			postCode = serialNumberStr;
		}
		sysPost.setPostCode(postCode);
		super.save(sysPost);		
		//保存岗位和数据权限的关联关系
		sysDatapermissionRelService.saveOrUpdatePostRel(sysPost.getPostId(), sysPost.getDataPermissionIdList());
		return true;
	}

	@Override
	@Transactional
	public boolean update(SysPost sysPost) {
		this.updateById(sysPost);
		sysDatapermissionRelService.saveOrUpdatePostRel(sysPost.getPostId(), sysPost.getDataPermissionIdList());
		return true;
	}
}
