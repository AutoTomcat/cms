package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.service.ICommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private ICommentService commentService;
	
	@GetMapping("findOneById")
	public Comment findOneById(long id) {
		return commentService.findOneById(id);
	}
	
	@GetMapping("/findAll")
	public List<Comment> findAll(){
		return commentService.findAll();
	}
	
	@PostMapping("deleteById")
	public void deleteById(long id) {
		commentService.deleteById(id);
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(Comment comment) {
		commentService.saveOrUpdate(comment);
		return "保存成功";
	}
}
