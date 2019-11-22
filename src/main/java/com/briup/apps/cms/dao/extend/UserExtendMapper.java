package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.extend.UserExtend;

public interface UserExtendMapper {
	
	UserExtend selectById(long id);
	
	UserExtend selectByName(String  username);
	
	List<UserExtend> selectAll();
	
}
