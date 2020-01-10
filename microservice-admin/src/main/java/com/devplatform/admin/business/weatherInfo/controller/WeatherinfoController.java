package com.devplatform.admin.business.weatherInfo.controller;

import java.util.*;

import com.devplatform.admin.business.weatherInfo.bean.Casts;
import com.devplatform.admin.business.weatherInfo.bean.WeatherinfoForecasts;
import com.devplatform.admin.business.weatherInfo.service.WeatherinfoForecastsService;
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

import com.devplatform.admin.business.weatherInfo.bean.Weatherinfo;
//import com.devplatform.admin.business.weatherInfo.model.WeatherinfoModel;
import com.devplatform.admin.business.weatherInfo.service.WeatherinfoService;
//import com.devplatform.admin.business.modules.sys.bean.SysUserEntity;



@Api(tags={"天气预报接口"})//swagger注解
@RestController
@RequestMapping("/weatherinfo")
public class WeatherinfoController extends AbstractController{
	
	
	@Autowired
	private WeatherinfoService weatherinfoService;
	@Autowired
	private WeatherinfoForecastsService weatherinfoForecastsService;
	
	
	/**
	 * 列表页面列表数据获取
	 * @return
	 */
    @ApiOperation(value="根据条件获取天气预报分页数据列表", notes="根据条件获取天气预报分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
	@PostMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = weatherinfoService.queryPage(params,null);
		return R.ok().put("page", page);
	}

	/**
	 * 列表页面列表数据获取
	 * @return
	 */
	@ApiOperation(value="根据条件获取天气预报分页数据列表", notes="根据条件获取天气预报分页数据列表")
	@ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
	@PostMapping("/listAll")
	public R listAll(@RequestParam Map<String, Object> params) {
		List<Weatherinfo> list = weatherinfoService.list(null);
		List<WeatherinfoForecasts> listF = weatherinfoForecastsService.list(new QueryWrapper<WeatherinfoForecasts>().orderByAsc(true,"date"));
		if(list!=null && list.size()>0){
			for(Weatherinfo bean:list){
				List<Casts> listC = new ArrayList<>();
				for(WeatherinfoForecasts beanF:listF){
					if(bean.getAdcode()!=null && bean.getAdcode().equals(beanF.getAdcode())){
						Casts cs = new Casts();
						cs.setDate(beanF.getDate());
						cs.setWeek(beanF.getWeek());
						cs.setDayweather(beanF.getDayweather());
						cs.setNightweather(beanF.getNightweather());
						cs.setDaytemp(beanF.getDaytemp());
						cs.setNighttemp(beanF.getNighttemp());
						cs.setDaypower(beanF.getDaypower());
						cs.setNightpower(beanF.getNightpower());
						cs.setDaywind(beanF.getDaywind());
						cs.setNightwind(beanF.getNightwind());
						listC.add(cs);
					}
				}
				bean.setCasts(listC);
			}
		}
		return R.ok().put("list", list);
	}


	
	/**
	 * 添加
	 * @param bean 天气预报对象
	 * @return
	 */
	@SysLog("添加天气预报")
	@ApiOperation(value="新增天气预报数据", notes="新增天气预报数据")
	@PostMapping("/save")
	public R save(@RequestBody @ApiParam(value="天气预报实体对象", required = true)Weatherinfo bean) throws Exception{
		ValidatorUtils.validateEntity(bean);
		weatherinfoService.save(bean);
		weatherinfoService.getGaodeWeathrinfo();
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param bean 天气预报对象
	 * @return
	 */
	@SysLog("修改天气预报")
	@ApiOperation(value="修改天气预报数据", notes="修改天气预报数据")
	@PostMapping("/update")
	public R update(@RequestBody @ApiParam(value="天气预报实体对象", required = true)Weatherinfo bean) {
		ValidatorUtils.validateEntity(bean);
		weatherinfoService.update(bean,
				new QueryWrapper<Weatherinfo>().like(StringUtil.checkNotNull(bean.getId()),"id", bean.getId()));
		return R.ok();
	}
	
	/**
	 * 根据ID获取天气预报对象
	 * @param id 对象主键
	 * @return
	 */
	@ApiOperation(value="根据ID获取天气预报对象", notes="根据ID获取天气预报对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
	@GetMapping("/getId/{id}")
	public R getId(@PathVariable String id) {
		Weatherinfo bean = weatherinfoService.getById(id);
		return R.ok().put("bean", bean);
	}
	
	
	
	/**
	 * 根据ID获取天气预报对象
	 * @return
	 */
	@SysLog("删除天气预报")
	@ApiOperation(value="根据ID批量删除天气预报数据", notes="根据ID批量删除天气预报数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
	@PostMapping("/delete")
	public R delete(@RequestBody String[] ids) {
		weatherinfoService.removeByIds(Arrays.asList(ids));
		return R.ok();
	}

}
