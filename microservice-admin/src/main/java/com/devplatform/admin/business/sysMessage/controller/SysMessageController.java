
package com.devplatform.admin.business.sysMessage.controller;

import java.util.Arrays;
import java.util.Date;
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
import com.devplatform.admin.business.sysMessage.bean.SysMessage;
//import com.devplatform.admin.business.sysMessage.model.SysMessageModel;
import com.devplatform.admin.business.sysMessage.service.SysMessageService;
//import com.devplatform.admin.business.modules.sys.bean.SysUserEntity;
//import com.devplatform.admin.business.modules.sys.bean.SysUserEntity;
import com.devplatform.admin.common.utils.AbstractController;
import com.devplatform.admin.config.BusinessEnum;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "消息模块接口" }) //swagger注解
@RestController
@RequestMapping("/sysMessage")
public class SysMessageController extends AbstractController {
    
    @Autowired
    private SysMessageService sysMessageService;
    
    /**
     * 列表页面列表数据获取
     * @param model 承接对象
     * @return
     */
    @ApiOperation(value = "根据条件获取消息模块分页数据列表", notes = "根据条件获取消息模块分页数据列表")
    @ApiImplicitParam(name = "params", value = "参数", required = true, dataType = "Map<String, Object>")
    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        
        String msgRecipientUserId = (String) params.get("msgRecipientUserId");
        String msgType = (String) params.get("msgType");
        String msgTitle = (String) params.get("msgTitle");
        String msgState = (String) params.get("msgState");
        Date msgCreateTimeStart = (Date) params.get("msgCreateTimeStart");
        Date msgCreateTimeEnd = (Date) params.get("msgCreateTimeEnd");
        PageUtils page = sysMessageService.queryPage(params,
                new QueryWrapper<SysMessage>().like(StringUtil.checkNotNull(msgTitle), "msg_title", msgTitle).eq(msgType != null, "msg_type", msgType)
                        .eq(msgState != null, "msg_state", msgState).eq(msgRecipientUserId != null, "msg_recipient_user_id", msgRecipientUserId)
                        .lt(StringUtil.isNotEmpty(msgCreateTimeStart), "msg_create_time", msgCreateTimeStart)
                        .gt(StringUtil.isNotEmpty(msgCreateTimeEnd), "msg_create_time", msgCreateTimeEnd).orderByDesc("msg_create_time"));
        return R.ok().put("page", page);
    }
    
    /**
     * 添加
     * @param bean 消息模块对象
     * @return
     */
    @SysLog("添加消息模块")
    @ApiOperation(value = "新增消息模块数据", notes = "新增消息模块数据")
    @PostMapping("/save")
    public R save(@RequestBody @ApiParam(value = "消息模块实体对象", required = true) SysMessage bean) {
        ValidatorUtils.validateEntity(bean);
        sysMessageService.save(bean);
        return R.ok();
    }
    
    /**
     * 修改
     * @param bean 消息模块对象
     * @return
     */
    @SysLog("修改消息模块")
    @ApiOperation(value = "修改消息模块数据", notes = "修改消息模块数据")
    @PostMapping("/update")
    public R update(@RequestBody @ApiParam(value = "消息模块实体对象", required = true) SysMessage bean) {
        ValidatorUtils.validateEntity(bean);
        sysMessageService.update(bean, new QueryWrapper<SysMessage>().like(StringUtil.checkNotNull(bean.getId()), "id", bean.getId()));
        return R.ok();
    }
    
    /**
     * 修改
     * @param bean 消息模块对象
     * @return
     */
    @SysLog("修改消息模块")
    @ApiOperation(value = "修改消息模块数据", notes = "修改消息模块数据")
    @PostMapping("/updateById")
    public R updateById(@RequestBody @ApiParam(value = "消息模块实体对象", required = true) SysMessage bean) {
        bean.setMsgCreateDisposeTime(new Date());
        sysMessageService.updateById(bean);
        return R.ok();
    }
    
    /**
     * 根据ID获取消息模块对象
     * @param id 对象主键
     * @return
     */
    @ApiOperation(value = "根据ID获取消息模块对象", notes = "根据ID获取消息模块对象")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    @GetMapping("/getId/{id}")
    public R getId(@PathVariable String id) {
        SysMessage bean = sysMessageService.getById(id);
        return R.ok().put("bean", bean);
    }
    
    /**
     * 根据ID获取消息模块对象
     * @param id[] 消息模块对象主键数组
     * @return
     */
    @SysLog("删除消息模块")
    @ApiOperation(value = "根据ID批量删除消息模块数据", notes = "根据ID批量删除消息模块数据")
    @ApiImplicitParam(name = "ids", value = "主键数组", required = true, dataType = "String")
    @PostMapping("/delete")
    public R delete(@RequestBody String[] ids) {
        sysMessageService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 所有消息类名称及key
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月11日下午7:48:29
     * @param params
     * @return
     */
    @ApiOperation(value = "所有消息类名称及key", notes = "所有消息类名称及key")
    @PostMapping("/getBusinessEnum")
    public R getBusinessEnum(@RequestBody Map<String, Object> params) {
        List<Map<String, Object>> list = BusinessEnum.getMessage_type();
        return R.ok().put("list", list);
    }
    
}
