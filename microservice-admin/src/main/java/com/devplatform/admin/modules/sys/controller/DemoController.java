package com.devplatform.admin.modules.sys.controller;

import java.util.*;

import com.devplatform.common.base.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devplatform.admin.modules.sys.bean.Demo;
import com.devplatform.admin.modules.sys.service.DemoService;
import com.devplatform.admin.modules.sys.bean.SysUserEntity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.admin.common.utils.AbstractController;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

@Api(tags={"demo演示权限接口"})//swagger注解
@RestController
@RequestMapping("/demo")
public class DemoController extends AbstractController{
	
	
	@Autowired
	private DemoService demoService; 
	
	
	/**
	 * 列表页面列表数据获取
	 * @return
	 */
    @ApiOperation(value="根据条件获取demo演示权限分页数据列表", notes="根据条件获取demo演示权限分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
        QueryWrapper queryWrapper = new QueryWrapper();
        String orderName = (String) params.get("orderName");
        if(null != orderName && !"".equals(orderName)){
            queryWrapper.like("order_name",orderName);
        }
        PageUtils page = demoService.queryPage(params,queryWrapper,getUser());
        return R.ok().put("page", page);
	}
	
	/**
	 * 添加
	 * @param bean demo演示权限对象
	 * @return
	 */
	@SysLog("添加demo演示权限")
	@ApiOperation(value="新增demo演示权限数据", notes="新增demo演示权限数据")
	@PostMapping("/save")
	public R save(@RequestBody @ApiParam(value="demo演示权限实体对象", required = true)Demo bean) {
		ValidatorUtils.validateEntity(bean);
		bean.setCreateUserId(getUserId());
		bean.setOrgId(getUser().getCurrentChoseOrgId());
		bean.setOrgCode(getUser().getCurrentChoseOrgCode());
		bean.setCreateTime(new Date());
		demoService.save(bean);
		return R.ok();
	}
	
	/**
	 * 根据ID获取demo演示权限对象
	 * @param id 对象主键
	 * @return
	 */
	@ApiOperation(value="根据ID获取demo演示权限对象", notes="根据ID获取demo演示权限对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
	@GetMapping("/getId/{id}")
	public R getId(@PathVariable String id) {
		Demo bean = demoService.getById(id);
		return R.ok().put("bean", bean);
	}

	/**
	 * 修改
	 * @param bean demo演示权限对象
	 * @return
	 */
	@SysLog("修改demo演示权限")
	@ApiOperation(value="修改demo演示权限数据", notes="修改demo演示权限数据")
	@PostMapping("/update")
	public R update(@RequestBody @ApiParam(value="demo演示权限实体对象", required = true)Demo bean) {
		ValidatorUtils.validateEntity(bean);
		demoService.update(bean,
				new QueryWrapper<Demo>().like(StringUtil.checkNotNull(bean.getId()),"id", bean.getId()));
		return R.ok();
	}

	
	
	/**
	 * 根据ID获取demo演示权限对象
	 * @param ids demo演示权限对象主键数组
	 * @return
	 */
	@SysLog("删除demo演示权限")
	@ApiOperation(value="根据ID批量删除demo演示权限数据", notes="根据ID批量删除demo演示权限数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
	@PostMapping("/delete")
	public R delete(@RequestBody String[] ids) {
		demoService.removeByIds(Arrays.asList(ids));
		return R.ok();
	}

}
