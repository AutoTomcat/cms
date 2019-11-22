package com.briup.apps.cms.service.Iml;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.bean.CommentExample;
import com.briup.apps.cms.dao.CommentMapper;
import com.briup.apps.cms.service.ICommentService;
@Service
public class CommentServiceImpl implements ICommentService{
	
	@Resource
	private CommentMapper commentMapper;
	
	@Override
	public List<Comment> findAll() {
		CommentExample commentExample =new CommentExample();
		
		return commentMapper.selectByExample(commentExample);
	}

	@Override
	public Comment findOneById(long id) {
		
		return commentMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveOrUpdate(Comment comment) {
		if(comment.getId()!=null) {
			commentMapper.updateByPrimaryKey(comment);
		}else {
			commentMapper.insert(comment);
		}
	}

	@Override
	public void deleteById(long id) {
		
		commentMapper.deleteByPrimaryKey(id);
	}

}
