package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.PrivilegeTree;

public interface IPrivilegeService {
	Privilege findOneById(long id);
	
	List<Privilege> findAll();
	
	void deleteById(long id);
	
	void saveOrUpdate(Privilege privilege) throws CustomerException;
	
	List<Privilege> findByParentId(Long parentId);
	
	List<PrivilegeTree> findPrivilegeTree();
	
	List<Privilege> findByUserId(long id);	
	
}
