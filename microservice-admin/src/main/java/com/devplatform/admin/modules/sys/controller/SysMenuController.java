package com.devplatform.admin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
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
import com.devplatform.admin.modules.sys.bean.SysMenuEntity;
import com.devplatform.admin.modules.sys.model.SysMenuModel;
import com.devplatform.admin.modules.sys.service.ShiroService;
import com.devplatform.admin.modules.sys.service.SysMenuService;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.exception.RRException;
import com.devplatform.common.util.Constant;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;

/**
 * 系统菜单
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午9:58:15
 */
@Api(tags={"系统菜单接口"})
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private ShiroService shiroService;

	/**
	 * 导航菜单
	 */
    @ApiOperation(value="获取导航菜单", notes="获取导航菜单")
	@GetMapping("/nav")
	public R nav(){
		List<SysMenuModel> menuList = sysMenuService.getUserMenuList(getUserId());
		Set<String> permissions = shiroService.getUserPermissions(getUserId());
		return R.ok().put("menuList", menuList).put("permissions", permissions);
	}
	
	/**
	 * 所有菜单列表
	 */
    @ApiOperation(value="根据条件获取分页数据列表", notes="根据条件获取分页数据列表")
    @ApiImplicitParam(name = "params", value = "查询参数", required = true,paramType="query", dataType = "Map<String, Object>")
	@GetMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public List<SysMenuEntity> list(@RequestParam Map<String, Object> params){
//		String name = (String)params.get("name");
//		PageUtils page = sysMenuService.queryPage(params,
//				new QueryWrapper<SysMenuEntity>().like(StringUtils.isNotBlank(name),"name", name));
//
//		return R.ok().put("page", page);
		List<SysMenuEntity> menuList = sysMenuService.list(null);
		for(SysMenuEntity sysMenuEntity : menuList){
			SysMenuEntity parentMenuEntity = sysMenuService.getById(sysMenuEntity.getParentId());
			if(parentMenuEntity != null){
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}

		return menuList;
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
    @ApiOperation(value="选择菜单", notes="选择菜单")
	@GetMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public R select(){
		//查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
		
		//添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId("0");
		root.setName("一级菜单");
		root.setParentId("-1");
		root.setOpen(true);
		menuList.add(root);
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 菜单信息
	 */
    @ApiOperation(value="根据ID获取菜单信息", notes="根据ID获取菜单信息")
    @ApiImplicitParam(name = "menuId", value = "菜单主键", required = true,paramType="query", dataType = "String")
	@GetMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public R info(@PathVariable("menuId") String menuId){
		SysMenuEntity menu = sysMenuService.getById(menuId);
		return R.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
    @ApiOperation(value="保存菜单", notes="保存菜单")
    @ApiImplicitParam(name = "menu", value = "菜单实体", required = true,paramType="query", dataType = "SysMenuEntity")
	@PostMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public R save(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
		sysMenuService.save(menu);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
    @ApiOperation(value="修改菜单", notes="修改菜单")
    @ApiImplicitParam(name = "menu", value = "菜单实体", required = true,paramType="query", dataType = "SysMenuEntity")
	@PostMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
				
		sysMenuService.updateById(menu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
    @ApiOperation(value="删除菜单", notes="删除菜单")
    @ApiImplicitParam(name = "menuId", value = "菜单实体", required = true,paramType="query", dataType = "String")
	@PostMapping("/delete/{menuId}")
	@RequiresPermissions("sys:menu:delete")
	public R delete(@PathVariable("menuId") String menuId){
		if(menuId.equals("1")){
			return R.error("系统菜单，不能删除");
		}

		//判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return R.error("请先删除子菜单或按钮");
		}

		sysMenuService.delete(menuId);

		return R.ok();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RRException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new RRException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == Constant.MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new RRException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = Constant.MenuType.CATALOG.getValue();
		if(!menu.getParentId().equals("0")){
			SysMenuEntity parentMenu = sysMenuService.getById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
				menu.getType() == Constant.MenuType.MENU.getValue()){
			if(parentType != Constant.MenuType.CATALOG.getValue()){
				throw new RRException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
			if(parentType != Constant.MenuType.MENU.getValue()){
				throw new RRException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
}
