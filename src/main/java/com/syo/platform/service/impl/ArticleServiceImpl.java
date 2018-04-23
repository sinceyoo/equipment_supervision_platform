package com.syo.platform.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.syo.platform.entity.Article;
import com.syo.platform.repository.ArticleRepository;
import com.syo.platform.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public void saveArticle(Article article) {
		articleRepository.save(article);
	}
	
	@Override
	public Page<Article> findArticleByAccount(String account, Date start, Date end, int pageNo, int pageSize,
			String vague) {
		PageRequest pageRequest = new PageRequest(pageNo-1, pageSize, Sort.Direction.DESC, "createTime");
		
		boolean isVagueNull = vague==null || vague.trim().equals("");
		boolean isDateNull = start==null || end==null;
		
		if(isVagueNull && isDateNull) {
			return articleRepository.findByAccount(pageRequest, account);
		}
		
		if(isVagueNull && !isDateNull) {
			return articleRepository.findByAccount(pageRequest, account, start, end);
		}
		
		if(!isVagueNull && isDateNull) {
			return articleRepository.findByAccount(pageRequest, account, vague);
		}
		
		if(!isVagueNull && isDateNull) {
			return articleRepository.findByAccount(pageRequest, account, start, end, vague);
		}
		
		return null;
	}

	@Override
	public Page<Article> findArticle(Date start, Date end, int pageNo, int pageSize, String vague) {
		PageRequest pageRequest = new PageRequest(pageNo-1, pageSize, Sort.Direction.DESC, "createTime");
		
		boolean isVagueNull = vague==null || vague.trim().equals("");
		boolean isDateNull = start==null || end==null;
		
		if(isVagueNull && isDateNull) {
			return articleRepository.findAll(pageRequest);
		}
		
		if(isVagueNull && !isDateNull) {
			return articleRepository.findAll(pageRequest, start, end);
		}
		
		if(!isVagueNull && isDateNull) {
			return articleRepository.findAll(pageRequest, vague);
		}
		
		if(!isVagueNull && isDateNull) {
			return articleRepository.findAll(pageRequest, start, end, vague);
		}
		
		return null;
	}

	@Override
	public Article findArticleById(String id) {
		return articleRepository.findOne(id);
	}

	@Override
	public void deleteArticle(String id) {
		articleRepository.delete(id);
	}

	

}
