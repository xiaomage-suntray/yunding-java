package ${bean.pacageName}${bean.modulesName}.controller;

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

import ${bean.pacageName}${bean.modulesName}.bean.${bean.className};
//import ${bean.pacageName}${bean.modulesName}.model.${bean.className}Model;
import ${bean.pacageName}${bean.modulesName}.service.${bean.className}Service;
//import ${bean.pacageName}modules.sys.bean.SysUserEntity;



@Api(tags={"${bean.tableRemark}接口"})//swagger注解
@RestController
@RequestMapping("/${bean.lowerName}")
public class ${bean.className}Controller extends AbstractController{
	
	
	@Autowired
	private ${bean.className}Service ${bean.lowerName}Service; 
	
	
	/**
	 * 列表页面列表数据获取
	 * @param model 承接对象
	 * @return
	 */
    @ApiOperation(value="根据条件获取${bean.tableRemark}分页数据列表", notes="根据条件获取${bean.tableRemark}分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
	@PostMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = ${bean.lowerName}Service.queryPage(params,null);
		return R.ok().put("page", page);
	}
	
	/**
	 * 添加
	 * @param bean ${bean.tableRemark}对象
	 * @return
	 */
	@SysLog("添加${bean.tableRemark}")
	@ApiOperation(value="新增${bean.tableRemark}数据", notes="新增${bean.tableRemark}数据")
	@PostMapping("/save")
	public R save(@RequestBody @ApiParam(value="${bean.tableRemark}实体对象", required = true)${bean.className} bean) {
		ValidatorUtils.validateEntity(bean);
		bean.setCreatedUserId(getUserId());
		bean.setCreatedTime(new Date());
		${bean.lowerName}Service.save(bean);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param bean ${bean.tableRemark}对象
	 * @return
	 */
	@SysLog("修改${bean.tableRemark}")
	@ApiOperation(value="修改${bean.tableRemark}数据", notes="修改${bean.tableRemark}数据")
	@PostMapping("/update")
	public R update(@RequestBody @ApiParam(value="${bean.tableRemark}实体对象", required = true)${bean.className} bean) {
		ValidatorUtils.validateEntity(bean);
		${bean.lowerName}Service.update(bean,
				new QueryWrapper<${bean.className}>().like(StringUtil.checkNotNull(bean.getId()),"id", bean.getId()));
		return R.ok();
	}
	
	/**
	 * 根据ID获取${bean.tableRemark}对象
	 * @param id 对象主键
	 * @return
	 */
	@ApiOperation(value="根据ID获取${bean.tableRemark}对象", notes="根据ID获取${bean.tableRemark}对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
	@GetMapping("/getId/{id}")
	public R getId(@PathVariable String id) {
		${bean.className} bean = ${bean.lowerName}Service.getById(id);
		return R.ok().put("bean", bean);
	}
	
	
	
	/**
	 * 根据ID获取${bean.tableRemark}对象
	 * @param id[] ${bean.tableRemark}对象主键数组
	 * @return
	 */
	@SysLog("删除${bean.tableRemark}")
	@ApiOperation(value="根据ID批量删除${bean.tableRemark}数据", notes="根据ID批量删除${bean.tableRemark}数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
	@PostMapping("/delete")
	public R delete(@RequestBody String[] ids) {
		${bean.lowerName}Service.removeByIds(Arrays.asList(ids));
		return R.ok();
	}

}
