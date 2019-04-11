package com.soft.jianyue.api.service.impl;

import com.soft.jianyue.api.entity.Article;
import com.soft.jianyue.api.entity.vo.ArticleVO;
import com.soft.jianyue.api.mapper.ArticleMapper;
import com.soft.jianyue.api.mapper.ImgMapper;
import com.soft.jianyue.api.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ImgMapper imgMapper;
    @Override
    public List<ArticleVO> selectAll() {
        List<ArticleVO> articles =articleMapper.selectAll();
        for (ArticleVO articleVO:articles){
            articleVO.setImgs(imgMapper.selectImgsByaId(articleVO.getId()));
        }
        return articles;
    }

    @Override
    public ArticleVO getArticleById(int aId) {
        return articleMapper.getArticleById(1);
    }

    @Override
    public List<ArticleVO> selectByUId(int uId) {
        return articleMapper.selectByUId(uId);
    }

    @Override
    public void insertArticle(Article article) {
        articleMapper.insertArticle(article);
    }
}
