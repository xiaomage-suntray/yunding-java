package com.devplatform.admin.modules.sys.service.impl;

import java.util.Date;

import com.devplatform.admin.modules.sys.bean.SysSerialNumber;
import com.devplatform.admin.modules.sys.service.SysOrgPrincipalService;
import com.devplatform.admin.modules.sys.service.SysSerialNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devplatform.admin.modules.sys.bean.SysOrg;
import com.devplatform.admin.modules.sys.bean.SysPost;
import com.devplatform.admin.modules.sys.dao.SysOrgDao;
import com.devplatform.admin.modules.sys.service.SysDatapermissionRelService;
import com.devplatform.admin.modules.sys.service.SysOrgService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

/**
 * 组织机构管理的service接口实现类
 * <br>
 * <b>功能：</b>SysOrgServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysOrgService")
public class SysOrgServiceImpl extends MyBaseServiceImpl<SysOrgDao, SysOrg> implements SysOrgService {

	@Autowired
	private SysDatapermissionRelService sysDatapermissionRelService;

	@Autowired
	private SysOrgPrincipalService sysOrgPrincipalService;

	@Autowired
	private SysSerialNumberService sysSerialNumberService;

	@Override
	@Transactional
	public boolean save(SysOrg sysOrg) {
		sysOrg.setCreateTime(new Date());
		//查询父节点的code.
		SysOrg tmpParentNode = this.getById(sysOrg.getParentId());
		String orgCode = "";
		SysSerialNumber tmpSerialNumber = new SysSerialNumber();
		sysSerialNumberService.insertForAutoIncr(tmpSerialNumber);
		String serialNumberStr = String.format("%04d",tmpSerialNumber.getSerialNumber());
		if(null != tmpParentNode && null != tmpParentNode.getOrgCode() && !"".equals(tmpParentNode.getOrgCode())){
			orgCode = tmpParentNode.getOrgCode() + "_" + serialNumberStr;
		}else{
			orgCode = serialNumberStr;
		}
		sysOrg.setOrgCode(orgCode);
		super.save(sysOrg);		
		//保存组织机构和数据权限的关联关系
		sysDatapermissionRelService.saveOrUpdateOrgRel(sysOrg.getOrgId(), sysOrg.getDataPermissionIdList());
		//保存组织机构和负责人的关联关系
		sysOrgPrincipalService.saveOrUpdateOrgRel(sysOrg.getOrgId(), sysOrg.getUserIdList(),sysOrg.getCreateUserId());
		return true;
	}

	@Override
	@Transactional
	public boolean update(SysOrg sysOrg) {
		this.updateById(sysOrg);
		sysDatapermissionRelService.saveOrUpdateOrgRel(sysOrg.getOrgId(), sysOrg.getDataPermissionIdList());
		//保存组织机构和负责人的关联关系
		sysOrgPrincipalService.saveOrUpdateOrgRel(sysOrg.getOrgId(), sysOrg.getUserIdList(),sysOrg.getCreateUserId());
		return true;
	}

}
