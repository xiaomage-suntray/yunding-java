
package com.devplatform.admin.modules.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devplatform.admin.common.utils.AbstractController;
import com.devplatform.admin.modules.sys.bean.SysFormDetail;
import com.devplatform.admin.modules.sys.service.SysFormDetailService;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "系统表单明细接口" }) //swagger注解
@RestController
@RequestMapping("/sys/formDetail")
public class SysFormDetailController extends AbstractController {
    
    @Autowired
    private SysFormDetailService sysFormDetailService;
    
    /**
     * 列表页面列表数据获取
     * @param model 承接对象
     * @return
     */
    @ApiOperation(value = "根据条件获取系统表单明细分页数据列表", notes = "根据条件获取系统表单明细分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
    @GetMapping("/list")
    //@RequiresPermissions("sys:formDetail:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysFormDetailService.queryPage(params, null);
        return R.ok().put("page", page);
    }
    
    /**
     * 添加
     * @param bean 系统表单明细对象
     * @return
     */
    @SysLog("添加系统表单明细")
    @ApiOperation(value = "新增系统表单明细数据", notes = "新增系统表单明细数据")
    @PostMapping("/save")
    //@RequiresPermissions("sys:formDetail:save")
    public R save(@RequestBody @ApiParam(value = "系统表单明细实体对象", required = true) SysFormDetail bean) {
        ValidatorUtils.validateEntity(bean);
        bean.setCreateUserId(getUserId());
        bean.setCreateTime(new Date());
        try {
            sysFormDetailService.save(bean);
        }
        catch (Exception e) {
            logger.error("添加系统表单明细时错误：" + e.getMessage());
            return R.error(500, "添加系统表单明细时错误：详情查看日志信息");
        }
        return R.ok();
    }
    
    /**
     * 修改
     * @param bean 系统表单明细对象
     * @return
     */
    @SysLog("修改系统表单明细")
    @ApiOperation(value = "修改系统表单明细数据", notes = "修改系统表单明细数据")
    @PostMapping("/update")
    //@RequiresPermissions("sys:formDetail:update")
    public R update(@RequestBody @ApiParam(value = "系统表单明细实体对象", required = true) SysFormDetail bean) {
        ValidatorUtils.validateEntity(bean);
        try {
            bean.setUpdateUserId(getUserId());
            bean.setUpdateTime(new Date());
            sysFormDetailService.update(bean, new QueryWrapper<SysFormDetail>().like(StringUtil.checkNotNull(bean.getId()), "id", bean.getId()));
        }
        catch (Exception e) {
            logger.error("添加系统表单明细时错误：" + e.getMessage());
            return R.error(500, "添加系统表单明细时错误：详情查看日志信息");
        }
        return R.ok();
    }
    
    /**
     * 根据ID获取系统表单明细对象
     * @param id 对象主键
     * @return
     */
    @ApiOperation(value = "根据ID获取系统表单明细对象", notes = "根据ID获取系统表单明细对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    @GetMapping("/getId/{id}")
    //@RequiresPermissions("sys:formDetail:get")
    public R getId(@PathVariable String id) {
        SysFormDetail bean = sysFormDetailService.getById(id);
        return R.ok().put("bean", bean);
    }
    
    /**
     * 根据ID获取系统表单明细对象
     * @param id[] 系统表单明细对象主键数组
     * @return
     */
    @SysLog("删除系统表单明细")
    @ApiOperation(value = "根据ID批量删除系统表单明细数据", notes = "根据ID批量删除系统表单明细数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
    @PostMapping("/delete")
    //@RequiresPermissions("sys:formDetail:delete")
    public R delete(@RequestBody String[] ids) {
        sysFormDetailService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 批量添加
     * @param bean 系统表单明细对象
     * @return
     */
    @SysLog("批量添加系统表单明细")
    @ApiOperation(value = "批量新增系统表单明细数据", notes = "批量新增系统表单明细数据")
    @PostMapping("/batchSave")
    //@RequiresPermissions("sys:formDetail:batchSave")
    public R batchSave(@RequestBody @ApiParam(value = "JSON字符串或JSON字符串数组", required = true) String jStr) {
        ValidatorUtils.validateEntity(jStr);
        
        //解析前端数据
        JSONObject toJsonObj = (JSONObject) JSON.parse(jStr);
        String key = toJsonObj.get("jStr").toString();
        JSONArray jArr = JSON.parseArray(key);
        
        try {
            List<SysFormDetail> detailList = new ArrayList<SysFormDetail>();
            for (Object o : jArr) {
                JSONObject jo = (JSONObject) o;
                
                String formId = jo.getString("formId");
                String discriptionName = jo.getString("discriptionName");
                String propertyName = jo.getString("propertyName");
                String columnName = jo.getString("columnName");
                String remark = jo.getString("remark");
                Integer delFlag = jo.getInteger("delFlag");
                String status = jo.getString("status");
                Integer type = jo.getInteger("type");
                Integer sort = jo.getInteger("sort");
                
                SysFormDetail detail = new SysFormDetail();
                detail.setFormId(formId);
                detail.setDiscriptionName(discriptionName);
                detail.setPropertyName(propertyName);
                detail.setColumnName(columnName);
                detail.setCreateTime(new Date());
                detail.setCreateUserId(getUserId());
                detail.setRemark(remark);
                detail.setDelFlag(delFlag);
                detail.setStatus(status);
                detail.setType(type);
                detail.setSort(sort);
                detailList.add(detail);
            }
            
            sysFormDetailService.saveBatch(detailList);
        }
        catch (Exception e) {
            logger.error("批量添加系统表单明细时错误：" + e.getMessage());
            return R.error(500, "批量添加系统表单明细时错误：详情查看日志信息");
        }
        return R.ok();
    }
    
}
