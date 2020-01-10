package com.devplatform.admin.modules.sys.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devplatform.admin.modules.sys.bean.SysFormDetail;
import com.devplatform.admin.modules.sys.dao.SysFormDetailDao;
import com.devplatform.admin.modules.sys.service.SysFormDetailService;
import com.devplatform.admin.modules.sys.service.SysFormService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

import com.devplatform.admin.modules.sys.bean.SysFormDetail;

/**
 * 系统表单明细的service接口实现类
 * <br>
 * <b>功能：</b>SysFormDetailServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysFormDetailService")
public class SysFormDetailServiceImpl extends MyBaseServiceImpl<SysFormDetailDao, SysFormDetail> implements SysFormDetailService {

	@Autowired
	private SysFormDetailDao sysFormDetailDao;

 	/**
	 * 根据ID获取系统表单明细
	 * @param formId 表单对象主键
	 * @return List<SysFormDetail>
	 * @author songdx
	 */
	@Override
	public List<SysFormDetail> getChildrenById(String formId) {
		return sysFormDetailDao.getChildrenById(formId);
	}

	@Override
	public boolean saveBatch(Collection<SysFormDetail> entityList) {
		
		//删除原有的 重新保存
		List<SysFormDetail> sysFormDetailList = (List<SysFormDetail>) entityList;
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("form_id", sysFormDetailList.get(0).getFormId());
		sysFormDetailDao.deleteByMap(paramMap);
		
		return super.saveBatch(entityList);
	}

	/**
	 * 根据表单URL模糊查询获取系统表单明细
	 * @param url 表单地址
	 * @return
	 */
	@Override
	public List<SysFormDetail> getChildrenByUrlLike(String url) {
		return sysFormDetailDao.getChildrenByUrlLike(url);
	}

}
