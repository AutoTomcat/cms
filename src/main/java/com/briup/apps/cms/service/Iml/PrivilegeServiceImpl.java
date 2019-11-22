package com.briup.apps.cms.service.Iml;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.bean.PrivilegeExample;
import com.briup.apps.cms.dao.PrivilegeMapper;
import com.briup.apps.cms.dao.extend.PrivilegeExtendMapper;
import com.briup.apps.cms.service.IPrivilegeService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.PrivilegeTree;
@Service
public class PrivilegeServiceImpl implements IPrivilegeService{
	
	@Resource
	private PrivilegeMapper privilegeMapper;
	@Resource
	private PrivilegeExtendMapper privilegeExtendMapper;	
	@Override
	public List<Privilege> findAll() {
		PrivilegeExample privilegeExample =new PrivilegeExample();
		
		return privilegeMapper.selectByExample(privilegeExample);
	}

	@Override
	public Privilege findOneById(long id) {
		
		return privilegeMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveOrUpdate(Privilege privilege) throws CustomerException{
		if(privilege.getId()!=null) {
			privilegeMapper.updateByPrimaryKey(privilege);
		}else {
			privilegeMapper.insert(privilege);
		}
	}

	@Override
	public void deleteById(long id) {
		
		privilegeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Privilege> findByParentId(Long parentId) {
		 PrivilegeExample example = new PrivilegeExample();
	        if(parentId == null){
	            example.createCriteria().andParentIdIsNull();
	        } else {
	            example.createCriteria().andParentIdEqualTo(parentId);
	        }
	        return privilegeMapper.selectByExample(example);
	}

	@Override
	public List<PrivilegeTree> findPrivilegeTree() {
		return privilegeExtendMapper.selectAll();
	}

	@Override
	public List<Privilege> findByUserId(long id) {
		return privilegeExtendMapper.selectByUserId(id);
	}

}
