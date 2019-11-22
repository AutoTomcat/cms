package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.service.IPrivilegeService;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {
	
	@Autowired
	private IPrivilegeService privilegeService;
	
	@GetMapping("findOneById")
	public Privilege findOneById(long id) {
		return privilegeService.findOneById(id);
	}
	
	@GetMapping("/findAll")
	public List<Privilege> findAll(){
		return privilegeService.findAll();
	}
	
	@PostMapping("deleteById")
	public void deleteById(long id) {
		privilegeService.deleteById(id);
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(Privilege privilege) {
		privilegeService.saveOrUpdate(privilege);
		return "保存成功";
	}
}
