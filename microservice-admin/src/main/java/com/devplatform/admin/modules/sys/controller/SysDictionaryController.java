package com.devplatform.admin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devplatform.admin.common.utils.AbstractController;
import com.devplatform.admin.common.utils.UtilMethod;
import com.devplatform.admin.modules.sys.bean.SysDictionary;
import com.devplatform.admin.modules.sys.service.SysDictionaryService;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Api(tags = {"字典表接口"})//swagger注解
@RestController
@RequestMapping("/sysDictionary")
public class SysDictionaryController extends AbstractController {


    @Autowired
    private SysDictionaryService sysDictionaryService;


    /**
     * 列表页面列表数据获取
     *
     * @param params 承接对象
     * @return
     */
    @ApiOperation(value = "根据条件获取字典表分页数据列表", notes = "根据条件获取字典表分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //接受参数
        String dictionaryName = (String) params.get("dictionaryName");
        String dictionaryCalss = (String) params.get("dictionaryCalss");
        String dictionaryCode = (String) params.get("dictionaryCode");
        //查询
        PageUtils page = sysDictionaryService.queryPage(params,
                new QueryWrapper<SysDictionary>()
                        .like(StringUtil.checkNotNull(dictionaryName), "dictionary_name", dictionaryName)
                        .like(StringUtil.checkNotNull(dictionaryCalss), "dictionary_calss", dictionaryCalss)
                        .like(StringUtil.checkNotNull(dictionaryCode), "dictionary_code", dictionaryCode)
                        .orderByDesc("CREATED_TIME")
        );
        return R.ok().put("page", page);
    }

    /**
     * 添加
     *
     * @param bean 字典表对象
     * @return
     */
    @SysLog("添加字典表")
    @ApiOperation(value = "新增字典表数据", notes = "新增字典表数据")
    @PostMapping("/save")
    public R save(@RequestBody @ApiParam(value = "字典表实体对象", required = true) SysDictionary bean) {
        ValidatorUtils.validateEntity(bean);
        bean.setCreatedUserId(getUserId());
        bean.setCreatedTime(new Date());
        //生成编码
        int count = sysDictionaryService.list(
                new QueryWrapper<SysDictionary>()
                        .eq(StringUtil.checkNotNull(bean.getDictionaryCalss()), "dictionary_calss", bean.getDictionaryCalss())).size();
        int number = 1001 + count;
        String dictionaryCode = bean.getDictionaryCalss() + UtilMethod.subStr("" + number, 1);
        bean.setDictionaryCode(dictionaryCode);
        sysDictionaryService.save(bean);
        return R.ok();
    }

    /**
     * 修改
     *
     * @param bean 字典表对象
     * @return
     */
    @SysLog("修改字典表")
    @ApiOperation(value = "修改字典表数据", notes = "修改字典表数据")
    @PostMapping("/update")
    public R update(@RequestBody @ApiParam(value = "字典表实体对象", required = true) SysDictionary bean) {
        ValidatorUtils.validateEntity(bean);
        bean.setUpdatedTime(new Date());
        bean.setUpdatedUserId(getUserId());
        sysDictionaryService.update(bean,
                new QueryWrapper<SysDictionary>().like(StringUtil.checkNotNull(bean.getId()), "id", bean.getId()));
        return R.ok();
    }

    /**
     * 根据ID获取字典表对象
     *
     * @param id 对象主键
     * @return
     */
    @ApiOperation(value = "根据ID获取字典表对象", notes = "根据ID获取字典表对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    @GetMapping("/getId/{id}")
    public R getId(@PathVariable String id) {
        SysDictionary bean = sysDictionaryService.getById(id);
        return R.ok().put("bean", bean);
    }


    /**
     * 根据ID获取字典表对象
     *
     * @param ids 字典表对象主键数组
     * @return
     */
    @SysLog("删除字典表")
    @ApiOperation(value = "根据ID批量删除字典表数据", notes = "根据ID批量删除字典表数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
    @PostMapping("/delete")
    public R delete(@RequestBody String[] ids) {
        sysDictionaryService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 获取满足查询条件的字典列表
     *
     * @param sysDictionary 承接对象
     * @return
     */
    @ApiOperation(value = "获取满足查询条件的字典列表", notes = "获取满足查询条件的字典列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
    @PostMapping("/getListByPara")
    public R getListByPara(@RequestBody @ApiParam(value = "字典实体对象", required = true) SysDictionary sysDictionary) {
        List<SysDictionary> list = sysDictionaryService.getListByPara(sysDictionary);
        return R.ok().put("list", list);
    }
}
