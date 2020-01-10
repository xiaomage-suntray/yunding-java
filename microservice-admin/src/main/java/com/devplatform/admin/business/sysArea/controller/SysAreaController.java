package com.devplatform.admin.business.sysArea.controller;

import java.util.*;

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

import com.devplatform.admin.business.sysArea.bean.SysArea;
//import com.devplatform.admin.business.sysArea.model.SysAreaModel;
import com.devplatform.admin.business.sysArea.service.SysAreaService;
//import com.devplatform.admin.business.modules.sys.bean.SysUserEntity;



@Api(tags={"区域表接口"})//swagger注解
@RestController
@RequestMapping("/sysArea")
public class SysAreaController extends AbstractController{
	
	
	@Autowired
	private SysAreaService sysAreaService; 
	
	
	/**
	 * 区域列表页面列表数据获取
	 * @author heibin
	 * 2019年3月22日上午10:10:59
	 * @param model 承接对象
	 * @return
	 */
    @ApiOperation(value="根据条件获取区域表分页数据列表", notes="根据条件获取区域表分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
	@PostMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		/*PageUtils page = sysAreaService.queryPage(params,null);
		return R.ok().put("page", page);*/
		SysArea  sysArea =new SysArea();
		//查询所有区域
		List<SysArea> list = sysAreaService.getListByPara(sysArea);
		//一级分类
		List<SysArea> listFirst = new ArrayList<SysArea>();
		for (SysArea g : list) {
			if (StringUtil.isEmpty(g.getParentId())) {
				List<SysArea> listFirsttwo = new ArrayList<SysArea>();
				for (SysArea gg : list) {
					if (!StringUtil.isEmpty(gg.getParentId())) {
						if (gg.getParentId().equals(g.getId())) {
							listFirsttwo.add(gg);
						}
					}
				}
				g.setChildren(listFirsttwo);
				listFirst.add(g);
			}
		}

		return R.ok().put("result", listFirst);
	}
	
	/**
	 * 区域添加
	 * @author heibin
	 * 2019年3月22日上午10:10:59
	 * @param bean 区域表对象
	 * @return
	 */
	@SysLog("添加区域表")
	@ApiOperation(value="新增区域表数据", notes="新增区域表数据")
	@PostMapping("/save")
	public R save(@RequestBody @ApiParam(value="区域表实体对象", required = true)SysArea bean) {
		ValidatorUtils.validateEntity(bean);
		bean.setCreatedUserId(getUserId());
		bean.setCreatedTime(new Date());
		sysAreaService.save(bean);
		return R.ok();
	}
	
	/**
	 * 区域修改
	 * @author heibin
	 * 2019年3月22日上午10:10:59
	 * @param bean 区域表对象
	 * @return
	 */
	@SysLog("修改区域表")
	@ApiOperation(value="修改区域表数据", notes="修改区域表数据")
	@PostMapping("/update")
	public R update(@RequestBody @ApiParam(value="区域表实体对象", required = true)SysArea bean) {
		ValidatorUtils.validateEntity(bean);
		sysAreaService.updateSysArea(bean);
		return R.ok();
	}
	
	/**
	 * 区域根据ID获取区域表对象
	 * @author heibin
	 * 2019年3月22日上午10:10:59
	 * @param id 对象主键
	 * @return
	 */
	@ApiOperation(value="根据ID获取区域表对象", notes="根据ID获取区域表对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
	@GetMapping("/getId/{id}")
	public R getId(@PathVariable String id) {
		SysArea bean = sysAreaService.getById(id);
		return R.ok().put("bean", bean);
	}
	
	
	
	/**
	 * 区域根据ID删除区域表对象
	 * @author heibin
	 * 2019年3月22日上午10:10:59
	 * @param id[] 区域表对象主键数组
	 * @return
	 */
	@SysLog("删除区域表")
	@ApiOperation(value="根据ID批量删除区域表数据", notes="根据ID批量删除区域表数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
	@PostMapping("/delete")
	public R delete(@RequestBody SysArea bean) {
		sysAreaService.deleteSysArea(bean);
		return R.ok();
	}

}
