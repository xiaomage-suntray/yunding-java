package com.devplatform.admin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devplatform.admin.modules.sys.bean.SysLogEntity;
import com.devplatform.admin.modules.sys.service.SysLogService;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;


/**
 * 系统日志
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-08 10:40:56
 */
@Api(tags = {"系统日志接口"})
@RestController
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @ApiOperation(value = "根据条件获取分页数据列表", notes = "根据条件获取分页数据列表")
    @ApiImplicitParam(name = "params", value = "查询参数", required = true, paramType = "query", dataType = "Map<String, Object>")
    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    public R list(@RequestParam Map<String, Object> params) {
        //获取参数

        String username = (String) params.get("username");
        String operation = (String) params.get("operation");
        String createTimeStar = (String) params.get("createTimeStar");
        String createTimeEnd = (String) params.get("createTimeEnd");
        //处理日期
        Date dateStar = new Date();
        Date dateEnd = new Date();
        if(StringUtil.isNotEmpty(createTimeStar)){
            dateStar.setTime(Long.parseLong(createTimeStar));
        }
        if(StringUtil.isNotEmpty(createTimeEnd)){
            dateEnd.setTime(Long.parseLong(createTimeEnd));
        }
        //查询参数
        PageUtils page = sysLogService.queryPage(params,
                new QueryWrapper<SysLogEntity>()
                        .like(StringUtil.checkNotNull(username), "username", username)
                        .like(StringUtil.checkNotNull(operation), "operation", operation)
                        .gt(StringUtil.checkNotNull(createTimeStar), "create_date",dateStar)
                        .lt(StringUtil.checkNotNull(createTimeEnd), "create_date",dateEnd)
                        .orderByDesc("create_date")
        );
        return R.ok().put("page", page);
    }

}
