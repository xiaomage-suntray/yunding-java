
package com.devplatform.admin.modules.sys.controller;

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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devplatform.admin.common.utils.AbstractController;
import com.devplatform.admin.modules.sys.bean.SysForm;
import com.devplatform.admin.modules.sys.bean.SysFormDetail;
import com.devplatform.admin.modules.sys.service.SysFormService;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "系统表单接口" }) //swagger注解
@RestController
@RequestMapping("/sys/form")
public class SysFormController extends AbstractController {
    
    @Autowired
    private SysFormService sysFormService;
    
    /**
     * 列表页面列表数据获取
     * @param model 承接对象
     * @return
     */
    @ApiOperation(value = "根据条件获取系统表单分页数据列表", notes = "根据条件获取系统表单分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
    @GetMapping("/list")
    ////@RequiresPermissions("sys:form:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysFormService.queryPage(params, null);
        return R.ok().put("page", page);
    }
    
    /**
     * 添加
     * @param bean 系统表单对象
     * @return
     */
    @SysLog("添加系统表单")
    @ApiOperation(value = "新增系统表单数据", notes = "新增系统表单数据")
    @PostMapping("/save")
    //@RequiresPermissions("sys:form:save")
    public R save(@RequestBody @ApiParam(value = "系统表单实体对象", required = true) SysForm bean) {
        ValidatorUtils.validateEntity(bean);
        bean.setCreateUserId(getUserId());
        bean.setCreateTime(new Date());
        try {
            sysFormService.save(bean);
        }
        catch (Exception e) {
            logger.error("添加系统表单错误：" + e.getMessage());
            return R.error(500, "添加系统表单错误：详情查看日志信息");
        }
        return R.ok();
    }
    
    /**
     * 修改
     * @param bean 系统表单对象
     * @return
     */
    @SysLog("修改系统表单")
    @ApiOperation(value = "修改系统表单数据", notes = "修改系统表单数据")
    @PostMapping("/update")
    //@RequiresPermissions("sys:form:update")
    public R update(@RequestBody @ApiParam(value = "系统表单实体对象", required = true) SysForm bean) {
        ValidatorUtils.validateEntity(bean);
        try {
            sysFormService.update(bean, new QueryWrapper<SysForm>().like(StringUtil.checkNotNull(bean.getId()), "id", bean.getId()));
        }
        catch (Exception e) {
            logger.error("更新系统表单错误：" + e.getMessage());
            return R.error(500, "更新系统表单错误：详情查看日志信息");
        }
        return R.ok();
    }
    
    /**
     * 根据ID获取系统表单对象
     * @param id 对象主键
     * @return
     */
    @ApiOperation(value = "根据ID获取系统表单对象", notes = "根据ID获取系统表单对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    @GetMapping("/getId/{id}")
    //@RequiresPermissions("sys:form:get")
    public R getId(@PathVariable String id) {
        SysForm bean = sysFormService.getById(id);
        return R.ok().put("bean", bean);
    }
    
    /**
     * 根据表单ID获取系统表单明细
     * @param id 对象主键
     * @return
     */
    @ApiOperation(value = "根据表单ID获取系统表单明细", notes = "根据ID获取系统表单明细")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    @GetMapping("/getChildrenById/{id}")
    //@RequiresPermissions("sys:form:getChildrenById")
    public R getChildrenById(@PathVariable String id) {
        List<SysFormDetail> detailList = sysFormService.getChildrenById(id);
        return R.ok().put("detailList", detailList);
    }
    
    /**
     * 根据表单URL模糊查询获取系统表单明细
     * @param url 表单地址
     * @return
     */
    @ApiOperation(value = "根据表单URL模糊查询获取系统表单明细", notes = "根据表单URL获取系统表单明细")
    @ApiImplicitParam(name = "url", value = "表单地址", required = true, dataType = "String")
    @GetMapping("/getChildrenByUrlLike")
    //@RequiresPermissions("sys:form:getChildrenByUrlLike")
    public R getChildrenByUrlLike(@RequestParam String url) {
        List<SysFormDetail> detailList = sysFormService.getChildrenByUrlLike(url);
        return R.ok().put("detailList", detailList);
    }
    
    /**
     * 根据ID获取系统表单对象
     * @param id[] 系统表单对象主键数组
     * @return
     */
    @SysLog("删除系统表单")
    @ApiOperation(value = "根据ID批量删除系统表单数据", notes = "根据ID批量删除系统表单数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
    @PostMapping("/delete")
    //@RequiresPermissions("sys:form:delete")
    public R delete(@RequestBody String[] ids) {
        sysFormService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
}
