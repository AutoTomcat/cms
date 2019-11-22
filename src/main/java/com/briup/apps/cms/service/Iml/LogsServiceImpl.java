package com.briup.apps.cms.service.Iml;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Logs;
import com.briup.apps.cms.bean.LogsExample;
import com.briup.apps.cms.dao.LogsMapper;
import com.briup.apps.cms.service.ILogsService;

@Service
public class LogsServiceImpl implements ILogsService{
	
	@Resource
	private LogsMapper logsMapper; 
	
	@Override
	public List<Logs> findAll() {
		LogsExample logsExample =new LogsExample();
		
		return logsMapper.selectByExample(logsExample);
	}

	@Override
	public Logs findOneById(long id) {
		
		return logsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveOrUpdate(Logs logs) {
		if(logs.getId()!=null) {
			logsMapper.updateByPrimaryKey(logs);
		}else {
			logsMapper.insert(logs);
		}
	}

	@Override
	public void deleteById(long id) {
		
		logsMapper.deleteByPrimaryKey(id);
	}


}
