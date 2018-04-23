package com.syo.platform.service;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.syo.platform.entity.Article;

public interface ArticleService {

	void saveArticle(Article article);
	
	Page<Article> findArticle(Date start, Date end, int pageNo, int pageSize, String vague);
	
	Page<Article> findArticleByAccount(String account, Date start, Date end, int pageNo, int pageSize, String vague);
	
	Article findArticleById(String id);
	
	void deleteArticle(String id);
	 
}
