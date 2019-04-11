package com.soft.jianyue.api.controller;

import com.soft.jianyue.api.entity.Article;
import com.soft.jianyue.api.entity.Follow;
import com.soft.jianyue.api.entity.Img;
import com.soft.jianyue.api.entity.vo.ArticleVO;
import com.soft.jianyue.api.entity.vo.CommentVO;
import com.soft.jianyue.api.service.ArticleService;
import com.soft.jianyue.api.service.CommentService;
import com.soft.jianyue.api.service.FollowService;
import com.soft.jianyue.api.service.ImgService;
import com.soft.jianyue.api.util.MsgConst;
import com.soft.jianyue.api.util.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @Resource
    private CommentService commentService;

    @Resource
    private ImgService imgService;
    @Resource
    private FollowService followService;

    @GetMapping(value = "/list")
    public ResponseResult getAll() {
        List<ArticleVO> articleList = articleService.selectAll();
        return ResponseResult.success(articleList);
    }

    @GetMapping(value = "/{aId}")
    public ResponseResult getArticleById(@PathVariable("aId") int aId, @RequestParam("userId") int userId) {
        ArticleVO article = articleService.getArticleById(aId);
        int toUId = article.getUId();
        Map<String, Object> map = new HashMap<>();
        Follow follow = followService.getFollow(userId, toUId);
            if (follow != null) {
                map.put("followed", MsgConst.FOLLOWED);
            } else {
            map.put("followed", MsgConst.NO_FOLLOWED);
        }
        List<CommentVO> comments = commentService.selectCommentsByAId(aId);
        map.put("article", article);
        map.put("comments", comments);
        return ResponseResult.success(map);
    }
//    @GetMapping(value = "/{uId}")
//    public ResponseResult getArticleById(@PathVariable("uId") int uId,@RequestParam("userId") int userId) {
//        Article article = articleService.selectByUId(uId);
//        int toUId = article.getUId();
//        Map<String, Object> map = new HashMap<>();
//        Follow follow = followService.getFollow(userId, toUId);
//        if (follow != null) {
//            map.put("followed", MsgConst.FOLLOWED);
//        } else {
//            map.put("followed", MsgConst.NO_FOLLOWED);
//        }
//        map.put("article", article);
//        return ResponseResult.success(map);
//    }
    @PostMapping("/add")
    public ResponseResult postArticle(@RequestParam("uId") int uId,
                                      @RequestParam("title") String title,
                                      @RequestParam("content") String content) {
        Article article = new Article();
        article.setUId(uId);
        article.setTitle(title);
        article.setContent(content);
        article.setCreateTime(new Date());
        articleService.insertArticle(article);
        //新增文章后，将获取到的自增主键返回给客户端，方便图片地址的写入
        return ResponseResult.success(article.getId());
    }

}