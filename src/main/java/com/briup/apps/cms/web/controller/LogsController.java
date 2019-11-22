package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Logs;
import com.briup.apps.cms.service.ILogsService;

@RestController
@RequestMapping("/logs")
public class LogsController {
	
	@Autowired
	private ILogsService logsService;
	
	@GetMapping("findOneById")
	public Logs findOneById(long id) {
		return logsService.findOneById(id);
	}
	
	@GetMapping("/findAll")
	public List<Logs> findAll(){
		return logsService.findAll();
	}
	
	@PostMapping("deleteById")
	public void deleteById(long id) {
		logsService.deleteById(id);
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(Logs logs) {
		logsService.saveOrUpdate(logs);
		return "保存成功";
	}
}
