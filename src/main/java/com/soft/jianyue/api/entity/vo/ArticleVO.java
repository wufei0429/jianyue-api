package com.soft.jianyue.api.entity.vo;

import com.soft.jianyue.api.entity.Img;

import java.util.Date;
import java.util.List;

import  lombok.Data;
@Data
public class ArticleVO {
    private Integer id;
    private Integer uId;
    private String title;
    private String content;
    private Date createTime;
    private String nickname;
    private String avatar;
    private List<Img> imgs;
}
