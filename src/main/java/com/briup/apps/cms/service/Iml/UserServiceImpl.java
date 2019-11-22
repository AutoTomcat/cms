package com.briup.apps.cms.service.Iml;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.UserExample;
import com.briup.apps.cms.bean.UserRole;
import com.briup.apps.cms.bean.UserRoleExample;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.dao.UserMapper;
import com.briup.apps.cms.dao.UserRoleMapper;
import com.briup.apps.cms.dao.extend.UserExtendMapper;
import com.briup.apps.cms.service.IUserService;
import com.briup.apps.cms.utils.CustomerException;
@Service
public class UserServiceImpl implements IUserService{
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserExtendMapper  userExtendMapper;
	@Resource
	private UserRoleMapper  userRoleMapper;
	
	@Override
	public UserExtend findById(long id) {
		return userExtendMapper.selectById(id);
	}

	@Override
	public UserExtend findByName(String username) {
		return userExtendMapper.selectByName(username);
	}

	@Override
	public List<User> findAll() {
		UserExample example = new UserExample();
		return userMapper.selectByExample(example);
	}

	@Override
	public List<UserExtend> cascadeFindAll() {
		return userExtendMapper.selectAll();
	}

	@Override
	public void deleteById(long id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void saveOrUpdate(UserExtend userExtend) throws CustomerException {
		if(userExtend.getId()!=null) {
			userMapper.updateByPrimaryKey(userExtend);
		}else {
			//用户名不能重名
			UserExample example = new UserExample();
			example.createCriteria().andNameEqualTo(userExtend.getName());
			
			List<User> userList = userMapper.selectByExample(example);
			if(userList.size()>0) {
				throw new CustomerException("用户名不能重名");
			}
			
			//初始化
			userExtend.setRegisterTime(new Date().getTime());
			userMapper.insert(userExtend);
		}
	}

	@Override
	public void setRoles(long id, List<Long> roles) {
		
		  // 根据userid查询自己的角色
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(id);
        // 用户角色关系,获取所有老的角色
        List<UserRole> list = userRoleMapper.selectByExample(example);
        List<Long> oldRoles = new ArrayList<>();
        Iterator<UserRole> iterator = list.iterator();
        while(iterator.hasNext()){
            oldRoles.add(iterator.next().getRoleId());
        }
        
        // [1,2,3,4] -> [1,2] 删除 3,4 
        // 依次判断老的角色是否存在于roles中，如果不存在则删除
        for(UserRole userRole : list){
            if(!roles.contains(userRole.getRoleId())){
                UserRoleExample example1 = new UserRoleExample();
                userRoleMapper.deleteByPrimaryKey(userRole.getId());
            }
        }
        
        // [3,4] -> [1,2,3,4] 添加1,2 
        // 依次判断新角色是否存在于list中，如果不存在则添加
        for(Long roleId : roles){
            if(!oldRoles.contains(roleId)){
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(id);
                userRoleMapper.insert(userRole);
            }
        }
        
	}
	

}
