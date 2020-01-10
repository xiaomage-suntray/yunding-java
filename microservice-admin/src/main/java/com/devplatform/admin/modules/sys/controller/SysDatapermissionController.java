
package com.devplatform.admin.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
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
import com.devplatform.admin.modules.sys.bean.SysDatapermission;
import com.devplatform.admin.modules.sys.service.SysDatapermissionService;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "数据权限表接口" }) //swagger注解
@RestController
@RequestMapping("/sysDatapermission")
public class SysDatapermissionController extends AbstractController {
    
    @Autowired
    private SysDatapermissionService sysDatapermissionService;
    
    /**
     * 列表页面列表数据获取
     * @return
     */
    @ApiOperation(value = "根据条件获取数据权限表分页数据列表", notes = "根据条件获取数据权限表分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDatapermissionService.queryPage(params, null);
        return R.ok().put("page", page);
    }
    
    /**
     * 添加
     * @param bean 数据权限表对象
     * @return
     */
    @SysLog("添加数据权限表")
    @ApiOperation(value = "新增数据权限表数据", notes = "新增数据权限表数据")
    @PostMapping("/save")
    public R save(@RequestBody @ApiParam(value = "数据权限表实体对象", required = true) SysDatapermission bean) {
        ValidatorUtils.validateEntity(bean);
        bean.setCreateUserId(getUserId());
        bean.setCreateTime(new Date());
        sysDatapermissionService.save(bean);
        return R.ok();
    }
    
    /**
     * 修改
     * @param bean 数据权限表对象
     * @return
     */
    @SysLog("修改数据权限表")
    @ApiOperation(value = "修改数据权限表数据", notes = "修改数据权限表数据")
    @PostMapping("/update")
    public R update(@RequestBody @ApiParam(value = "数据权限表实体对象", required = true) SysDatapermission bean) {
        ValidatorUtils.validateEntity(bean);
        sysDatapermissionService.update(bean, new QueryWrapper<SysDatapermission>().eq(StringUtil.checkNotNull(bean.getDatapermissionId()),
                "datapermission_id", bean.getDatapermissionId()));
        return R.ok();
    }
    
    /**
     * 根据ID获取数据权限表对象
     * @param id 对象主键
     * @return
     */
    @ApiOperation(value = "根据ID获取数据权限表对象", notes = "根据ID获取数据权限表对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    @GetMapping("/getId/{id}")
    public R getId(@PathVariable String id) {
        SysDatapermission bean = sysDatapermissionService.getById(id);
        return R.ok().put("bean", bean);
    }
    
    /**
     * 根据ID获取数据权限表对象
     * @param id[] 数据权限表对象主键数组
     * @return
     */
    @SysLog("删除数据权限表")
    @ApiOperation(value = "根据ID批量删除数据权限表数据", notes = "根据ID批量删除数据权限表数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
    @PostMapping("/delete")
    public R delete(@RequestBody String[] ids) {
        sysDatapermissionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
}
