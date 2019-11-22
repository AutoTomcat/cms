package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleWithBLOBs;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.utils.CustomerException;

public interface IArticleService {
	
	List<Article>  findAll();
	
	List<ArticleExtend> cascadeFindAll();
	
	void saveOrUpdate(ArticleWithBLOBs articleWithBLOBs)throws CustomerException;
	
	void deleteById(long id);
	
	ArticleWithBLOBs findOneById(long id);
	
	ArticleExtend findById(long id);
}
