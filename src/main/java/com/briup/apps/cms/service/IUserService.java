package com.briup.apps.cms.service;


import java.util.List;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.utils.CustomerException;

public interface IUserService {
	
	UserExtend findById(long id);
	
	UserExtend findByName(String  username);
	
    List<User>  findAll();
	
	List<UserExtend> cascadeFindAll();
	
	void deleteById(long id);
	
	void saveOrUpdate(UserExtend userExtend) throws CustomerException;
	
	void setRoles(long id, List<Long> roles);
}
