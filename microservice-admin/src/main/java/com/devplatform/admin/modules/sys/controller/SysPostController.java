
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
import com.devplatform.admin.modules.sys.bean.SysPost;
import com.devplatform.admin.modules.sys.service.SysDatapermissionRelService;
import com.devplatform.admin.modules.sys.service.SysPostService;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "岗位管理接口" }) //swagger注解
@RestController
@RequestMapping("/sys/post")
public class SysPostController extends AbstractController {
    
    @Autowired
    private SysPostService sysPostService;
    
    @Autowired
    private SysDatapermissionRelService sysDatapermissionRelService;
    
    /**
     * 列表页面列表数据获取
     * @return
     */
    @ApiOperation(value = "根据条件获取岗位管理分页数据列表", notes = "根据条件获取岗位管理分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        String postName = (String) params.get("postname");
        System.out.println(postName);
        PageUtils page = sysPostService.queryPage(params, new QueryWrapper<SysPost>().like(StringUtil.checkNotNull(postName), "post_name", postName));
        return R.ok().put("page", page);
    }
    
    /**
     * 添加
     * @param bean 岗位管理对象
     * @return
     */
    @SysLog("添加岗位管理")
    @ApiOperation(value = "新增岗位管理数据", notes = "新增岗位管理数据")
    @PostMapping("/save")
    public R save(@RequestBody @ApiParam(value = "岗位管理实体对象", required = true) SysPost bean) {
        ValidatorUtils.validateEntity(bean);
        bean.setCreateUserId(getUserId());
        sysPostService.save(bean);
        return R.ok();
    }
    
    /**
     * 修改
     * @param bean 岗位管理对象
     * @return
     */
    @SysLog("修改岗位管理")
    @ApiOperation(value = "修改岗位管理数据", notes = "修改岗位管理数据")
    @PostMapping("/update")
    public R update(@RequestBody @ApiParam(value = "岗位管理实体对象", required = true) SysPost bean) {
        ValidatorUtils.validateEntity(bean);
        sysPostService.update(bean);
        return R.ok();
    }
    
    /**
     * 根据ID获取岗位管理对象
     * @param id 对象主键
     * @return
     */
    @ApiOperation(value = "根据ID获取岗位管理对象", notes = "根据ID获取岗位管理对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    @GetMapping("/getId/{id}")
    public R getId(@PathVariable String id) {
        SysPost bean = sysPostService.getById(id);
        //缓存岗位对应的信息
        List<SysDatapermissionRel> sysDatapermissionList = sysDatapermissionRelService
                .list(new QueryWrapper<SysDatapermissionRel>().eq("post_id", bean.getPostId()));
        List<String> dataPermissionIdList = new ArrayList<String>();
        for (SysDatapermissionRel sdmr : sysDatapermissionList) {
            dataPermissionIdList.add(sdmr.getDatapermissionId());
        }
        bean.setDataPermissionIdList(dataPermissionIdList);
        return R.ok().put("bean", bean);
    }
    
    /**
     * 根据ID获取岗位管理对象
     * @param ids 岗位管理对象主键数组
     * @return
     */
    @SysLog("删除岗位管理")
    @ApiOperation(value = "根据ID批量删除岗位管理数据", notes = "根据ID批量删除岗位管理数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
    @PostMapping("/delete")
    public R delete(@RequestBody String[] ids) {
        sysPostService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
}
