package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.utils.CustomerException;

public interface ICategoryService {
	
	Category findOneById(long id);
	
	List<Category> findAll();
	
	void deleteById(long id) throws CustomerException;
	
	void saveOrUpdate(Category category)throws CustomerException;
	
	void batchDelete(long[] ids) throws CustomerException;
	
}
