package com.briup.apps.cms.service.Iml;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.bean.ArticleWithBLOBs;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.CustomerException;
@Service
public class ArticleServiceImpl implements IArticleService{
	
	
	
	@Resource
	private  ArticleMapper articleMapper;
	@Resource
	private ArticleExtendMapper articleExtendMapper;
	
	@Override
	public List<Article> findAll() {
		ArticleExample example = new ArticleExample();
		return articleMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(ArticleWithBLOBs articleWithBLOBs) throws CustomerException{
		if(articleWithBLOBs.getId()!=null) {
			articleMapper.updateByPrimaryKey(articleWithBLOBs);
		}else {
			//标题不能重名
			ArticleExample example = new ArticleExample();
			example.createCriteria().andTitleEqualTo(articleWithBLOBs.getTitle());
			
			List<ArticleWithBLOBs> artiList = articleMapper.selectByExampleWithBLOBs(example);
			if(artiList.size()>0) {
				throw new CustomerException("标题不能重名");
			}
			
			//初始化
			articleWithBLOBs.setPublishTime(new Date().getTime());
			articleWithBLOBs.setStatus(ArticleExtend.STATUS_UNCHECK);
			articleWithBLOBs.setThumbDown(0l);
			articleWithBLOBs.setThumbUp(0l);
			articleMapper.insert(articleWithBLOBs);
		}
	}

	@Override
	public void deleteById(long id) {
		articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ArticleWithBLOBs findOneById(long id) {
		
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ArticleExtend> cascadeFindAll() {
		return articleExtendMapper.selectAll();
	}

	@Override
	public ArticleExtend findById(long id) {
		return articleExtendMapper.selectById(id);
	}
	
}
