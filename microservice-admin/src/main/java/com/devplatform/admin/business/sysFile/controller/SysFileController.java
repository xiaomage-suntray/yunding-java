package com.devplatform.admin.business.sysFile.controller;

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
//import com.devplatform.admin.business.modules.sys.bean.SysUserEntity;
import com.devplatform.admin.common.utils.AbstractController;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.devplatform.admin.business.sysFile.bean.SysFile;
//import com.devplatform.admin.business.sysFile.model.SysFileModel;
import com.devplatform.admin.business.sysFile.service.SysFileService;
//import com.devplatform.admin.business.modules.sys.bean.SysUserEntity;



@Api(tags={"系统文件表接口"})//swagger注解
@RestController
@RequestMapping("/sysFile")
public class SysFileController extends AbstractController{
	
	
	@Autowired
	private SysFileService sysFileService; 
	
	
	/**
	 * 列表页面列表数据获取
	 * @param model 承接对象
	 * @return
	 */
    @ApiOperation(value="根据条件获取系统文件表分页数据列表", notes="根据条件获取系统文件表分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
	@PostMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = sysFileService.queryPage(params,null);
		return R.ok().put("page", page);
	}
	
	/**
	 * 添加
	 * @param bean 系统文件表对象
	 * @return
	 */
	@SysLog("添加系统文件表")
	@ApiOperation(value="新增系统文件表数据", notes="新增系统文件表数据")
	@PostMapping("/save")
	public R save(@RequestBody @ApiParam(value="系统文件表实体对象", required = true)SysFile bean) {
		ValidatorUtils.validateEntity(bean);
		bean.setCreatedUserId(getUserId());
		bean.setCreatedTime(new Date());
		sysFileService.save(bean);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param bean 系统文件表对象
	 * @return
	 */
	@SysLog("修改系统文件表")
	@ApiOperation(value="修改系统文件表数据", notes="修改系统文件表数据")
	@PostMapping("/update")
	public R update(@RequestBody @ApiParam(value="系统文件表实体对象", required = true)SysFile bean) {
		ValidatorUtils.validateEntity(bean);
		sysFileService.update(bean,
				new QueryWrapper<SysFile>().like(StringUtil.checkNotNull(bean.getId()),"id", bean.getId()));
		return R.ok();
	}
	
	/**
	 * 根据ID获取系统文件表对象
	 * @param id 对象主键
	 * @return
	 */
	@ApiOperation(value="根据ID获取系统文件表对象", notes="根据ID获取系统文件表对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
	@GetMapping("/getId/{id}")
	public R getId(@PathVariable String id) {
		SysFile bean = sysFileService.getById(id);
		return R.ok().put("bean", bean);
	}
	
	
	
	/**
	 * 根据ID获取系统文件表对象
	 * @param id[] 系统文件表对象主键数组
	 * @return
	 */
	@SysLog("删除系统文件表")
	@ApiOperation(value="根据ID批量删除系统文件表数据", notes="根据ID批量删除系统文件表数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
	@PostMapping("/delete")
	public R delete(@RequestBody String[] ids) {
		sysFileService.removeByIds(Arrays.asList(ids));
		return R.ok();
	}

}
