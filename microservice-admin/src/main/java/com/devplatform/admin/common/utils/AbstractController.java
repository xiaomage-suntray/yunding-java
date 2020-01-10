
package com.devplatform.admin.common.utils;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devplatform.admin.common.model.BaseModel;
import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.common.controller.BaseController;

/**
 * Controller公共组件
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController extends BaseController {
    
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }
    
    protected String getUserId() {
        return getUser().getUserId();
    }
    
    /**
     * 分页类
     * 
     * @param list
     * @param model
     * @return
     * @throws Exception
     *             MyPage
     * @throws @author
     *             Rice
     * @date 2017年9月22日
     */
    public <T> MyPage getMyPage(List<T> list, BaseModel model) {
        MyPage page = new MyPage();
        page.setRows(list);
        page.setTotal(model.getPager().getRowCount());
        return page;
    }
}
