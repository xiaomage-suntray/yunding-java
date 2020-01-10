package com.devplatform.admin.modules.sys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.devplatform.admin.modules.sys.bean.SysConfigEntity;
import com.devplatform.admin.modules.sys.service.SysConfigService;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

/**
 * 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:55:53
 */
@Api(tags={"系统配置接口"})
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
	@Autowired
	private SysConfigService sysConfigService;
	
	/**
	 * 所有配置列表
	 */
    @ApiOperation(value="根据条件获取分页数据列表", notes="根据条件获取分页数据列表")
    @ApiImplicitParam(name = "params", value = "查询参数", required = true,paramType="query", dataType = "Map<String, Object>")
    @GetMapping("/list")
	@RequiresPermissions("sys:config:list")
	public R list(@RequestParam Map<String, Object> params){
		String paramKey = (String)params.get("paramKey");
		PageUtils page = sysConfigService.queryPage(params,
				new QueryWrapper<SysConfigEntity>()
				.like(StringUtil.checkNotNull(paramKey),"param_key", paramKey)
				.eq("status", 1));
		return R.ok().put("page", page);
	}
	
	
	/**
	 * 配置信息
	 */
    @ApiOperation(value="根据ID获取配置信息", notes="根据ID获取配置信息")
    @ApiImplicitParam(name = "id", value = "主键", required = true,paramType="query", dataType = "String")
	@GetMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public R info(@PathVariable("id") String id){
		SysConfigEntity config = sysConfigService.getById(id);
		
		return R.ok().put("config", config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
    @ApiOperation(value="保存配置信息", notes="保存配置信息")
    @ApiImplicitParam(name = "config", value = "配置信息实体", required = true,paramType="query", dataType = "SysConfigEntity")
	@PostMapping("/save")
	@RequiresPermissions("sys:config:save")
	public R save(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);

		sysConfigService.save(config);
		
		return R.ok();
	}
	
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
    @ApiOperation(value="修改配置信息", notes="修改配置信息")
    @ApiImplicitParam(name = "config", value = "配置信息实体", required = true,paramType="query", dataType = "SysConfigEntity")
	@PostMapping("/update")
	@RequiresPermissions("sys:config:update")
	public R update(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);
		
		sysConfigService.update(config);
		
		return R.ok();
	}
	
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
    @ApiOperation(value="删除配置信息", notes="删除配置信息")
    @ApiImplicitParam(name = "config", value = "配置信息实体", required = true,paramType="query", dataType = "SysConfigEntity")
	@PostMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public R delete(@RequestBody String[] ids){
		sysConfigService.deleteBatch(ids);
		
		return R.ok();
	}

}
