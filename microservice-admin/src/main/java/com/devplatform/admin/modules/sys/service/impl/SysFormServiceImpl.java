package com.devplatform.admin.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devplatform.admin.modules.sys.bean.SysForm;
import com.devplatform.admin.modules.sys.bean.SysFormDetail;
import com.devplatform.admin.modules.sys.dao.SysFormDao;
import com.devplatform.admin.modules.sys.service.SysFormDetailService;
import com.devplatform.admin.modules.sys.service.SysFormService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

import com.devplatform.admin.modules.sys.bean.SysForm;

/**
 * 系统表单的service接口实现类
 * <br>
 * <b>功能：</b>SysFormServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysFormService")
public class SysFormServiceImpl extends MyBaseServiceImpl<SysFormDao, SysForm> implements SysFormService {

	@Autowired
	private SysFormDetailService sysFormDetailService; 
	
	/**
	 * 根据ID获取系统表单明细
	 * @param formId 表单对象主键
	 * @return List<SysFormDetail>
	 * @author songdx
	 */
	@Override
	public List<SysFormDetail> getChildrenById(String formId) {
		return sysFormDetailService.getChildrenById(formId);
	}

	/**
	 * 根据表单URL模糊查询获取系统表单明细
	 * @param url 表单地址
	 * @return
	 */
	@Override
	public List<SysFormDetail> getChildrenByUrlLike(String url) {
		return sysFormDetailService.getChildrenByUrlLike(url);
	}

}
