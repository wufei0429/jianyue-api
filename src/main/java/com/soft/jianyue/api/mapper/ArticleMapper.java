package com.soft.jianyue.api.mapper;

import com.soft.jianyue.api.entity.Article;
import com.soft.jianyue.api.entity.vo.ArticleVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "uId", column = "u_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "avatar", column = "avatar")
    })
    /*@Select("SELECT a.id, a.u_id, a.title, a.content, a.create_time, b.nickname, b.avatar, c.img_url " +
            "FROM t_article AS a LEFT JOIN t_user AS b ON a.id = c.a_id " +
            "LEFT JOIN t_img AS c ON a.id = c.a_id")*/
    @Select("select a.*,b.nickname,b.avatar from t_article a left Join t_user b on a.u_id = b.id order by id desc")
    List<ArticleVO> selectAll();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "uId", column = "u_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "avatar", column = "avatar")
    })
    @Select("SELECT a.*, b.nickname, b.avatar FROM t_article AS a LEFT JOIN t_user AS b ON a.u_id = b.id WHERE a.id = #{aId}")
    ArticleVO getArticleById(int aId);

    @Results({
            @Result(property = "uId", column = "u_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("SELECT * FROM t_article  WHERE u_id=#{id}")
    List<ArticleVO> selectByUId(int uId);

    @Insert("INSERT INTO t_article (u_id,title,content,create_time) VALUES (#{uId},#{title},#{content},#{createTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertArticle(Article article);
}
