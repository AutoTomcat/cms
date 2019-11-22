package com.briup.apps.cms.web.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleWithBLOBs;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private IArticleService articleService;
	
	@ApiOperation(value = "查询所有文章", notes = "级联所有栏目，作者")
	@GetMapping("findAll")
	public Message findAll(){
		List<Article> list = articleService.findAll();
		return MessageUtil.success(list);
	}
	
	@ApiOperation(value = "通过id查询")
	@ApiImplicitParams(
			@ApiImplicitParam(name="id",value="主键",paramType = "query"))
	@GetMapping("findOneById")
	public Message findOneById(long id) {
		 ArticleWithBLOBs articleWithBLOBs = articleService.findOneById(id);
		return MessageUtil.success(articleWithBLOBs);
	}
	
	@ApiOperation(value="保存或更新文章",notes="如果参数中包含id后端认为是更新操作，如果参数中不含id则认为是插入操作")
	@ApiImplicitParams({
			@ApiImplicitParam(name="id",value="编号",paramType = "form",required = false),
			@ApiImplicitParam(name="title",value="标题",paramType = "form",required = true),
			@ApiImplicitParam(name="content",value="内容",paramType = "form",required = false),
			@ApiImplicitParam(name="source",value="源内容",paramType = "form",required = false),
			@ApiImplicitParam(name="categoryId",value="所属栏目id",paramType = "form",required = false),
			@ApiImplicitParam(name="authorId",value="所属作者id",paramType = "form",required = false)
	})
	@PostMapping("saveOrUpdate")
	public Message saveOrUpdate(
			Long id,
			@NotNull String title,
			String content,
		    String source,
			Long categoryId,
			Long authorId) {
		ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
		articleWithBLOBs.setId(id);
		articleWithBLOBs.setTitle(title);
		articleWithBLOBs.setContent(content);
		articleWithBLOBs.setSource(source);
		articleWithBLOBs.setCategoryId(categoryId);
		articleWithBLOBs.setAuthorId(authorId);
		articleService.saveOrUpdate(articleWithBLOBs);
		return MessageUtil.success("更新成功");
	}
	
	@ApiOperation(value="通过id删除")
	@PostMapping("deleteById")
	public Message deleteById(long id) {
		articleService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
	
	@ApiOperation(value = "级联查询文章",notes="级联所属栏目，作者")
	@GetMapping("cascadeFindAll")
	public Message cascadeFindAll(){
		List<ArticleExtend> articleExtends = articleService.cascadeFindAll();
		return  MessageUtil.success(articleExtends);
	}
	
	@ApiOperation("通过id级联查询")
	@GetMapping("findById")
	public Message findById(long id) {
		ArticleExtend articleExtend = articleService.findById(id);
		return MessageUtil.success(articleExtend);
	}
	
	
}
