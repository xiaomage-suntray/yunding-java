package com.devplatform.admin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.admin.modules.sys.service.MyCustomizeBaseService;
import com.devplatform.common.dao.MyBaseMapper;
import com.devplatform.common.service.MyBaseService;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 自定义的baseService实现类
 * @author: wsh
 * @create: 2019-01-07 14:32
 **/
public class MyCustomizeBaseServiceImpl<M extends MyBaseMapper<T>, T> extends ServiceImpl<MyBaseMapper<T>, T> implements MyCustomizeBaseService<T> {

    @Autowired
    protected M baseMapper;

    public MyCustomizeBaseServiceImpl() {
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, QueryWrapper<T> queryWrapper, SysUserEntity userEntity) {
        if(null == queryWrapper){
            queryWrapper = new QueryWrapper<T>();
        }
        List<String> tmpDataPermissionStr = userEntity.getDataPermissionStr();
        if(null != tmpDataPermissionStr && tmpDataPermissionStr.size() > 0){
            List<String> dataList = new ArrayList<String>();
            StringBuffer sbf = new StringBuffer();
            sbf.append("(");
            for(int i = 0 ; i < tmpDataPermissionStr.size() ; i++){
                String str = tmpDataPermissionStr.get(i);
                if(sbf.length() > 1){
                    sbf.append(" or ");
                }
                if(str.indexOf("create_user_id") > -1){
                    sbf.append(str.replace("currentCreateUser",i+""));
                    dataList.add(userEntity.getUserId());
                }else if(str.indexOf("org_code=") > -1){
                    sbf.append(str.replace("currentDepartment",i+""));
                    dataList.add(userEntity.getCurrentChoseOrgCode());
                }else if(str.indexOf("org_code like") > -1){
                    sbf.append(str.replace("currentDepartmentAndLower",i+""));
                    dataList.add(userEntity.getCurrentChoseOrgCode()+"%");
                }
            }
            sbf.append(")");
            queryWrapper.apply(sbf.length()>2,sbf.toString(),dataList.toArray());
        }
        IPage<T> page = this.page((new Query(params)).getPage(), queryWrapper);
        return new PageUtils(page);
    }
}
