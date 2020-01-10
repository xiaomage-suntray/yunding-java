package com.devplatform.admin.modules.sys.service;

import com.devplatform.common.service.MyBaseService;

import java.util.List;

import com.devplatform.admin.modules.sys.bean.SysForm;
import com.devplatform.admin.modules.sys.bean.SysFormDetail;

/**
 * 系统表单的service接口
 * <br>
 * <b>功能：</b>SysFormService<br>
 * @author 代码生成器产生
 */
public interface SysFormService extends MyBaseService<SysForm> {

	/**
	 * 根据ID获取系统表单明细
	 * @param formId 对象主键
	 * @return
	 */
	List<SysFormDetail> getChildrenById(String formId);

	/**
	 * 根据表单URL模糊查询获取系统表单明细
	 * @param url 表单地址
	 * @return
	 */
	List<SysFormDetail> getChildrenByUrlLike(String url);

}
