package com.soft.jianyue.api.service.impl;

import com.soft.jianyue.api.entity.Article;
import com.soft.jianyue.api.entity.vo.ArticleVO;
import com.soft.jianyue.api.service.ArticleService;
import com.soft.jianyue.api.service.ImgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {
    @Resource
    private ArticleService articleService;
    @Resource
    private ImgService imgService;


    @Test
    public void selectAll() {
        //List<ArticleVO> list = new ArrayList<>();
        //list = articleService.selectAll();
        List<ArticleVO> articles =articleService.selectAll();
        for (ArticleVO articleVO:articles){
            articleVO.setImgs(imgService.selectImgsByaId(articleVO.getId()));
        }
        System.out.println(articles);
    }

    @Test
    public void getArticleById() {
        ArticleVO articleVO=articleService.getArticleById(1);
        System.out.println(articleVO);
    }
//    @Test
//    public void insertArticle(){
//        Article article = new Article();
//        article.setUId(2);
//        article.setTitle("紫色的梦");
//        article.setContent("<div>走进紫色的梦\n" +
//                "放眼紫色的花海\n" +
//                "细细碎碎的小花\n" +
//                "挤挤挨挨\n" +
//                "簇成锦花团团\n" +
//                "馨香着宁静\n" +
//                "充盈着守望的心灵\n" +
//                "这曼妙紫花\n" +
//                "从云空里取来蓝\n" +
//                "阳光中取来红\n" +
//                "月色里搬来白\n" +
//                "风行雾  雨缠绵\n" +
//                "才调均满山遍野的紫\n" +
//                "紫的云裳  紫的绮梦\n" +
//                "只为一个等待\n" +
//                "等你春风来\n" +
//                "等你持着银色月镰来\n" +
//                "春去秋来的风中\n" +
//                "你是不会凋零的花朵\n" +
//                "即便干枯在枝茎上\n" +
//                "一如不会凋零的诗行\n" +
//                "唱也是芬芳\n" +
//                "吟也是芬芳\n" +
//                "四月天空飘荡你的芳香\n" +
//                "淡烟流水\n" +
//                "为你上妆\n" +
//                "何惧只言片语\n" +
//                "深深眷恋\n" +
//                "薰衣草摇曳着\n" +
//                "仰望天空的浩际\n" +
//                "守护心的宁静\n" +
//                "你可知否\n" +
//                "我只为你——倾恋\n" +
//                "\n" +
//                "</div>");
//        article.setCreateTime(new Date());
//        articleService.insertArticle(article);
//    }
}