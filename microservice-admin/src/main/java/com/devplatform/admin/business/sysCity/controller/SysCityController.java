package com.devplatform.admin.business.sysCity.controller;

import java.util.*;

import com.devplatform.admin.business.sysCity.bean.SysCity;
import com.devplatform.admin.business.sysCity.service.SysCityService;
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
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Api(tags={"城市表接口"})//swagger注解
@RestController
@RequestMapping("/sysCity")
public class SysCityController extends AbstractController{
	
	
	@Autowired
	private SysCityService sysCityService;
	
	
	/**
	 * 列表页面列表数据获取
	 * @return
	 */
    @ApiOperation(value="根据条件获取城市表分页数据列表", notes="根据条件获取城市表分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
	@PostMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		QueryWrapper<SysCity> queryWrapper = new QueryWrapper<>();
    	if(params.get("cityname")!=null && !params.get("cityname").equals("")){
    		queryWrapper.like("cityname",params.get("cityname"));
		}
		PageUtils page = sysCityService.queryPage(params,queryWrapper);
		return R.ok().put("page", page);
	}
	
	/**
	 * 添加
	 * @param bean 城市表对象
	 * @return
	 */
	@SysLog("添加城市表")
	@ApiOperation(value="新增城市表数据", notes="新增城市表数据")
	@PostMapping("/save")
	public R save(@RequestBody @ApiParam(value="城市表实体对象", required = true) SysCity bean) {
		ValidatorUtils.validateEntity(bean);
		sysCityService.save(bean);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param bean 城市表对象
	 * @return
	 */
	@SysLog("修改城市表")
	@ApiOperation(value="修改城市表数据", notes="修改城市表数据")
	@PostMapping("/update")
	public R update(@RequestBody @ApiParam(value="城市表实体对象", required = true)SysCity bean) {
		ValidatorUtils.validateEntity(bean);
		sysCityService.update(bean,
				new QueryWrapper<SysCity>().like(StringUtil.checkNotNull(bean.getId()),"id", bean.getId()));
		return R.ok();
	}
	
	/**
	 * 根据ID获取城市表对象
	 * @param id 对象主键
	 * @return
	 */
	@ApiOperation(value="根据ID获取城市表对象", notes="根据ID获取城市表对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
	@GetMapping("/getId/{id}")
	public R getId(@PathVariable String id) {
		SysCity bean = sysCityService.getById(id);
		return R.ok().put("bean", bean);
	}
	
	
	
	/**
	 * 根据ID获取城市表对象
	 * @return
	 */
	@SysLog("删除城市表")
	@ApiOperation(value="根据ID批量删除城市表数据", notes="根据ID批量删除城市表数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
	@PostMapping("/delete")
	public R delete(@RequestBody String[] ids) {
		sysCityService.removeByIds(Arrays.asList(ids));
		return R.ok();
	}


	/**
	 * 全国城市导入
	 * @param file
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "importCityInfoExcel")
	//@ResponseBody
	public R importCityInfoExcel(@RequestParam(value = "file", required = true) MultipartFile file, RedirectAttributes redirectAttributes)
			throws Exception {
		Map map = new HashMap();
		sysCityService.importCityInfoExcel(file);
		return R.ok().put("map", map);
	}


}
