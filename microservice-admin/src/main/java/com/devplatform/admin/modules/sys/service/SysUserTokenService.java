package com.devplatform.admin.modules.sys.service;

import com.devplatform.admin.modules.sys.bean.SysUserTokenEntity;
import com.devplatform.common.service.MyBaseService;
import com.devplatform.common.util.R;

/**
 * 用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface SysUserTokenService extends MyBaseService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(String userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(String userId);

	/**
	 * 生成token
	 * @param token  用户token
	 * @param userId  用户ID
	 */
	R saveToken(String token,String userId);

}
