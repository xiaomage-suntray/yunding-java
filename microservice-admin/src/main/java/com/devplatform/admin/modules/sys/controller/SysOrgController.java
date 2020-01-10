
package com.devplatform.admin.modules.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import com.devplatform.admin.modules.sys.bean.SysDatapermissionRel;
import com.devplatform.admin.modules.sys.bean.SysOrg;
import com.devplatform.admin.modules.sys.bean.SysOrgPrincipal;
import com.devplatform.admin.modules.sys.service.SysDatapermissionRelService;
import com.devplatform.admin.modules.sys.service.SysOrgPrincipalService;
import com.devplatform.admin.modules.sys.service.SysOrgService;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "组织机构管理接口" }) //swagger注解
@RestController
@RequestMapping("/sys/org")
public class SysOrgController extends AbstractController {
    
    @Autowired
    private SysOrgService sysOrgService;
    
    @Autowired
    private SysDatapermissionRelService sysDatapermissionRelService;
    
    @Autowired
    private SysOrgPrincipalService sysOrgPrincipalService;
    
    /**
     * 列表页面列表数据获取
     * @return
     */
    @ApiOperation(value = "根据条件获取组织机构管理分页数据列表", notes = "根据条件获取组织机构管理分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        String orgName = (String) params.get("orgname");
        String orgCode = (String) params.get("orgCode");
        String isCorporation = (String) params.get("isCorporation");
        PageUtils page = sysOrgService.queryPage(params,
                new QueryWrapper<SysOrg>().like(StringUtil.checkNotNull(orgName), "org_name", orgName)
                        .likeRight(StringUtil.checkNotNull(orgCode), "org_code", orgCode)
                        .eq(StringUtil.checkNotNull(isCorporation), "is_corporation", isCorporation).orderByAsc(true, "sort"));
        return R.ok().put("page", page);
    }
    
    /**
     * 添加
     * @param bean 组织机构管理对象
     * @return
     */
    @SysLog("添加组织机构管理")
    @ApiOperation(value = "新增组织机构管理数据", notes = "新增组织机构管理数据")
    @PostMapping("/save")
    public R save(@RequestBody @ApiParam(value = "组织机构管理实体对象", required = true) SysOrg bean) {
        ValidatorUtils.validateEntity(bean);
        bean.setCreateUserId(getUserId());
        sysOrgService.save(bean);
        return R.ok();
    }
    
    /**
     * 修改
     * @param bean 组织机构管理对象
     * @return
     */
    @SysLog("修改组织机构管理")
    @ApiOperation(value = "修改组织机构管理数据", notes = "修改组织机构管理数据")
    @PostMapping("/update")
    public R update(@RequestBody @ApiParam(value = "组织机构管理实体对象", required = true) SysOrg bean) {
        ValidatorUtils.validateEntity(bean);
        sysOrgService.update(bean);
        return R.ok();
    }
    
    /**
     * 根据ID获取组织机构管理对象
     * @param id 对象主键
     * @return
     */
    @ApiOperation(value = "根据ID获取组织机构管理对象", notes = "根据ID获取组织机构管理对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    @GetMapping("/getId/{id}")
    public R getId(@PathVariable String id) {
        SysOrg bean = sysOrgService.getById(id);
        //缓存组织机构对应的信息
        List<SysDatapermissionRel> sysDatapermissionList = sysDatapermissionRelService
                .list(new QueryWrapper<SysDatapermissionRel>().eq("org_id", bean.getOrgId()));
        List<String> dataPermissionIdList = new ArrayList<String>();
        for (SysDatapermissionRel sdmr : sysDatapermissionList) {
            dataPermissionIdList.add(sdmr.getDatapermissionId());
        }
        bean.setDataPermissionIdList(dataPermissionIdList);
        //緩存组织机构负责人
        List<SysOrgPrincipal> sysOrgPrincipalList = sysOrgPrincipalService.list(new QueryWrapper<SysOrgPrincipal>().eq("org_id", bean.getOrgId()));
        List<String> principalIdList = new ArrayList<String>();
        for (SysOrgPrincipal sop : sysOrgPrincipalList) {
            principalIdList.add(sop.getUserId());
        }
        bean.setUserIdList(principalIdList);
        return R.ok().put("bean", bean);
    }
    
    /**
     * 根据ID获取组织机构管理对象
     * @param ids 组织机构管理对象主键数组
     * @return
     */
    @SysLog("删除组织机构管理")
    @ApiOperation(value = "根据ID批量删除组织机构管理数据", notes = "根据ID批量删除组织机构管理数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
    @PostMapping("/delete")
    public R delete(@RequestBody String[] ids) {
        sysOrgService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
}
