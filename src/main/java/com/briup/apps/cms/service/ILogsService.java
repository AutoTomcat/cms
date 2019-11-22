package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Logs;

public interface ILogsService {
	
	Logs findOneById(long id);
	
	List<Logs> findAll();
	
	void deleteById(long id);
	
	void saveOrUpdate(Logs logs);
}
