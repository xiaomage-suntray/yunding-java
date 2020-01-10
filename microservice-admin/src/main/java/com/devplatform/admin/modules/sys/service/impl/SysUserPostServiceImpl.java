package com.devplatform.admin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devplatform.admin.modules.sys.bean.SysUserPost;
import com.devplatform.admin.modules.sys.dao.SysUserPostDao;
import com.devplatform.admin.modules.sys.service.SysUserPostService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;
import com.devplatform.common.util.MapUtils;

/**
 * 用户岗位关系的service接口实现类
 * <br>
 * <b>功能：</b>SysUserPostServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysUserPostService")
public class SysUserPostServiceImpl extends MyBaseServiceImpl<SysUserPostDao, SysUserPost> implements SysUserPostService {

	@Override
	public void saveOrUpdate(String userId, List<String> postIdList) {
		//先删除用户与组织机构的关系
		this.removeByMap(new MapUtils().put("user_id", userId));

		if(postIdList == null || postIdList.size() == 0){
			return ;
		}

		//保存用户与组织机构关系
		List<SysUserPost> list = new ArrayList<>(postIdList.size());
		for(String postId : postIdList){
			SysUserPost sysUserPost = new SysUserPost();
			sysUserPost.setUserId(userId);
			sysUserPost.setPostId(postId);

			list.add(sysUserPost);
		}
		this.saveBatch(list);
	}

}
