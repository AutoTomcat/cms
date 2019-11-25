package com.briup.apps.cms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.service.IUserService;
import com.briup.apps.cms.utils.JwtTokenUtil;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.UserVM;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private  IUserService userServiceImpl;
	
	@PostMapping("login")
	public Message login(@RequestBody UserVM userVM) {
		// 1.认证用户的用户名和密码
		// 2.如果登录成功产生token,将token缓存起来,返回
		// 3.如果登录失败
		UserExtend  userExtend = userServiceImpl.findByName(userVM.getUsername());
		if(userExtend.getName()==null) {
			return MessageUtil.error("用户名不存在");
		}else {
			if(userVM.getUsername().equals(userExtend.getName())&&
			   userVM.getPassword().equals(userExtend.getPassword())) {
				Map<String,String> map = new HashMap<>();
				map.put("token", JwtTokenUtil.createJWT(userExtend.getId(), userExtend.getName()));
				return  MessageUtil.success(map);
			}
			return MessageUtil.error("密码错误");
		}
	}
	
	@ApiOperation(value="通过token获取用户的基本信息")
	@GetMapping("info")
	public Message info(String token) {
		// 1.通过token获取用户信息{id,use,gender,roles:[]}
		String username = token.substring(0, token.length()-6);
		UserExtend userExtend = userServiceImpl.findByName(username);
		return MessageUtil.success(userExtend);
	}
	
	@GetMapping("logout")
	public Message logout() {
		
		return MessageUtil.success("退出成功");
	}
	
	@GetMapping("findAll")
	@ApiOperation(value = "查询所有用户")
	public Message findAll() {
		List<User> list = userServiceImpl.findAll();
		return MessageUtil.success(list);
	}
	
	@GetMapping("cascadeFindAll")
	@ApiOperation("级联查询所有用户")
	public Message cascadeFindAll() {
		List<UserExtend> list = userServiceImpl.cascadeFindAll();
		return MessageUtil.success(list);
	}
	
	@PostMapping("findById")
	@ApiOperation("通过id查询")
	public Message findById(long id) {
		return MessageUtil.success(userServiceImpl.findById(id));
	}
	
	@PostMapping("findByName")
	@ApiOperation("通过name查询")
	public Message findByName(String username) {
		return MessageUtil.success(userServiceImpl.findByName(username));
	}
	
	@GetMapping("deleteById")
	@ApiOperation("通过id删除")
	public Message deleteById(long id) {
		userServiceImpl.deleteById(id);
		return MessageUtil.success("删除成功");
	}
	
	@PostMapping("saveOrUpdate")
	@ApiOperation(value="保存或更新用户",notes="如果参数中包含id后端认为是更新操作，如果参数中不含id则认为是插入操作")
	@ApiImplicitParams({
			@ApiImplicitParam(name="id",value="编号",paramType = "form",required = false),
			@ApiImplicitParam(name="name",value="用户名",paramType = "form",required = true),
			@ApiImplicitParam(name="password",value="密码",paramType = "form",required = true),
			@ApiImplicitParam(name="telephone",value="电话",paramType = "form",required = false),
			@ApiImplicitParam(name="realname",value="真实姓名",paramType = "form",required = false),
			@ApiImplicitParam(name="gender",value="性别",paramType = "form",required = false),
			@ApiImplicitParam(name="birth",value="生日",paramType = "form",required = false),
			@ApiImplicitParam(name="status",value="状态",paramType = "form",required = false),
			@ApiImplicitParam(name="userFace",value="头像",paramType = "form",required = false)
			})
	public Message saveOrUpdate(
			Long id,
			@NotNull String name,
			@NotNull String password,
			Long  telephone,
			String realname,
			String gender,
			Long birth,
			String status,
			String userFace
			){
		UserExtend userExtend = new UserExtend();
		userExtend.setId(id);
		userExtend.setName(name);
		userExtend.setPassword(password);
		userExtend.setTelephone(telephone);
		userExtend.setGender(gender);
		userExtend.setBirth(birth);
		userExtend.setStatus(status);
		userExtend.setUserFace(userFace);
		
		userServiceImpl.saveOrUpdate(userExtend);
		return MessageUtil.success("更新成功");
		
	}
	
}

