package com.briup.apps.cms.service.Iml;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.CustomerException;
@Service
public class CategoryServiceImpl implements ICategoryService{
	@Resource
	private CategoryMapper categoryMapper;
	
	
	@Override
	public Category findOneById(long id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Category> findAll() {
		CategoryExample example = new CategoryExample();
		return categoryMapper.selectByExample(example);
	}

	@Override
	@Transactional
	public void deleteById(long id) throws CustomerException{
		Category category = categoryMapper.selectByPrimaryKey(id);
		if(category == null) {
			throw new CustomerException("要删除的栏目不存在");
		}else {
			categoryMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void saveOrUpdate(Category category) throws CustomerException{
		if(category.getId()!=null) {
			categoryMapper.updateByPrimaryKey(category);
		}else {
			//判断是否重名
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(category.getName());
			
			List<Category> cateList = categoryMapper.selectByExample(example);
			if(cateList.size()>0) {
				throw new CustomerException("栏目已经存在");
			}
			categoryMapper.insert(category);
		}
	}

	@Override
	@Transactional
	public void batchDelete(long[] ids) throws CustomerException {
		System.out.println(ids+"----------");
		 for(long id:ids) {
			 this.deleteById(id);
		 }
	}

}
