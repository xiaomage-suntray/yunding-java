package com.devplatform.admin.business.carNumberLimit.controller;

import java.util.*;

import com.devplatform.admin.business.sysCity.bean.SysCity;
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

import com.devplatform.admin.business.carNumberLimit.bean.CarNumberLimit;
//import com.devplatform.admin.business.carNumberLimit.model.CarNumberLimitModel;
import com.devplatform.admin.business.carNumberLimit.service.CarNumberLimitService;



@Api(tags={"车辆限行接口"})//swagger注解
@RestController
@RequestMapping("/carNumberLimit")
public class CarNumberLimitController extends AbstractController{
	
	
	@Autowired
	private CarNumberLimitService carNumberLimitService; 
	
	
	/**
	 * 列表页面列表数据获取
	 * @return
	 */
    @ApiOperation(value="根据条件获取车辆限行分页数据列表", notes="根据条件获取车辆限行分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
	@PostMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = carNumberLimitService.queryPage(params,null);
		return R.ok().put("page", page);
	}
	
	/**
	 * 添加
	 * @param bean 车辆限行对象
	 * @return
	 */
	@SysLog("添加车辆限行")
	@ApiOperation(value="新增车辆限行数据", notes="新增车辆限行数据")
	@PostMapping("/save")
	public R save(@RequestBody @ApiParam(value="车辆限行实体对象", required = true)CarNumberLimit bean) {
		//全部改为失效，只展示现在的生效
		CarNumberLimit updateBean = new CarNumberLimit();
		updateBean.setStatus(2);
		carNumberLimitService.update(updateBean,null);
		List<CarNumberLimit> list = new ArrayList<>();
		if(bean.getCityNameList()!=null && bean.getCityNameList().size()>0){
			for(String cityname : bean.getCityNameList()){
				CarNumberLimit carNumberLimit =new CarNumberLimit();
				carNumberLimit.setCityname(cityname);
				carNumberLimit.setStartTime(bean.getStartTime());
				carNumberLimit.setEndTime(bean.getEndTime());
				carNumberLimit.setRules(bean.getRules());
				carNumberLimit.setCreateDate(new Date());
				carNumberLimit.setStatus(1);
				list.add(carNumberLimit);
			}
		}
		carNumberLimitService.saveBatch(list);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param bean 车辆限行对象
	 * @return
	 */
	@SysLog("修改车辆限行")
	@ApiOperation(value="修改车辆限行数据", notes="修改车辆限行数据")
	@PostMapping("/update")
	public R update(@RequestBody @ApiParam(value="车辆限行实体对象", required = true)CarNumberLimit bean) {
		bean.setCreateDate(new Date());
		carNumberLimitService.update(bean,
				new QueryWrapper<CarNumberLimit>().like(StringUtil.checkNotNull(bean.getId()),"id", bean.getId()));
		return R.ok();
	}
	
	/**
	 * 根据ID获取车辆限行对象
	 * @param id 对象主键
	 * @return
	 */
	@ApiOperation(value="根据ID获取车辆限行对象", notes="根据ID获取车辆限行对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
	@GetMapping("/getId/{id}")
	public R getId(@PathVariable String id) {
		CarNumberLimit bean = carNumberLimitService.getById(id);
		return R.ok().put("bean", bean);
	}
	
	
	
	/**
	 * 根据ID获取车辆限行对象
	 * @return
	 */
	@SysLog("删除车辆限行")
	@ApiOperation(value="根据ID批量删除车辆限行数据", notes="根据ID批量删除车辆限行数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
	@PostMapping("/delete")
	public R delete(@RequestBody String[] ids) {
		carNumberLimitService.removeByIds(Arrays.asList(ids));
		return R.ok();
	}

	/**
	 * 批量修改装填
	 * @return
	 */
	@SysLog("皮箱修改车辆限行")
	@ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
	@PostMapping("/bachUpdate")
	public R bachUpdate(@RequestBody @ApiParam(value="车辆限行实体对象", required = true)CarNumberLimit bean) {
		List<CarNumberLimit> list = new ArrayList<>();
		for(String id : bean.getIds()){
			CarNumberLimit newBean = new CarNumberLimit();
			newBean.setId(id);
			newBean.setStatus(bean.getStatus());
			newBean.setCreateDate(new Date());
			list.add(newBean);
		}
		carNumberLimitService.updateBatchById(list);
		return R.ok();
	}

}
