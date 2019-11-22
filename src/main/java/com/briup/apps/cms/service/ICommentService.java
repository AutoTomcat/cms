package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Comment;

public interface ICommentService {
	
	Comment findOneById(long id);
	
	List<Comment> findAll();
	
	void deleteById(long id);
	
	void saveOrUpdate(Comment comment);
}
