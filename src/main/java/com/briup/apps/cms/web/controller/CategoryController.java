package com.briup.apps.cms.web.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.google.common.annotations.VisibleForTesting;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("findOneById")
	public Message findOneById(long id) {
		 return MessageUtil.success(
				 categoryService.findOneById(id));
	}
	
	@ApiOperation(value="查询所有栏目")
	@GetMapping("findAll")
	public Message findAll(){
		 List<Category> list = categoryService.findAll();
		 return MessageUtil.success(list);
	}
	
	@ApiOperation(value="通过id删除")
	@GetMapping("deleteById")
	public Message deleteById(long id) {
		categoryService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
	
	@ApiOperation(value="保存或更新",notes="如果参数中包含id后端认为是更新操作，如果参数中不含id则认为是插入操作")
	@ApiImplicitParams({
			@ApiImplicitParam(name="id",value="主键",paramType = "form"),
			@ApiImplicitParam(name="name",value="栏目名称",paramType = "form",required = true),
			@ApiImplicitParam(name="description",value="栏目描述",paramType = "form"),
			@ApiImplicitParam(name="no",value="序号",paramType = "form"),
			@ApiImplicitParam(name="parentId",value="父栏目",paramType = "form"),
	})
	@PostMapping("saveOrUpdate")
	public Message saveOrUpdate(
			Long id,
			@NotNull String name,
			String description,
			Long  no,
			Long parentId
			){
		Category category = new Category();
		category.setId(id);
		category.setDescription(description);
		category.setName(name);
		category.setNo(no);
		category.setParentId(parentId);
		
		categoryService.saveOrUpdate(category);
		return MessageUtil.success("更新成功");
	}
	
	@ApiOperation("批量删除")
	@PostMapping("batchDelete")
	public Message batchDelete(long[] ids) {
		categoryService.batchDelete(ids);
		return MessageUtil.success("删除成功");
	}
}
