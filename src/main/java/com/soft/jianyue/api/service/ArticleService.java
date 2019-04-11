package com.soft.jianyue.api.service;

import com.soft.jianyue.api.entity.Article;
import com.soft.jianyue.api.entity.vo.ArticleVO;

import java.util.List;

public interface ArticleService {

    List<ArticleVO> selectAll();

    ArticleVO getArticleById(int aId);

    List<ArticleVO> selectByUId(int uId);

    void insertArticle(Article article);
}
