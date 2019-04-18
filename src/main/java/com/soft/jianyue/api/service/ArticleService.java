package com.soft.jianyue.api.service;

import com.soft.jianyue.api.entity.Article;
import com.soft.jianyue.api.entity.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {

    List<ArticleVO> selectAll();

    ArticleVO getArticleById(int aId);

    List<Article> selectByuId(int uId);

    void insertArticle(Article article);

    List<Article> selectByPage(int currPage,int count);
}
