
package com.devplatform.admin.business.wechat.dao;

import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.business.wechat.bean.WeixinAccessToken;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * WeixinAccessToken Mapper
 * 用于微信保存accessToken的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface WeixinAccessTokenDao extends MyBaseMapper<WeixinAccessToken> {
    
    /**
     * 获取最新accessToken
     * Description:
     * Company:
     * @author suochaochao
     * 2019年4月19日下午4:10:51
     * @return
     */
    WeixinAccessToken queryByNewAccessToken();
    
}
