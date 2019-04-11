package com.soft.jianyue.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.soft.jianyue.api.entity.Img;
import com.soft.jianyue.api.service.ImgService;
import com.soft.jianyue.api.util.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/img")
public class ImgController {
    @Resource
    private ImgService imgService;
    @PostMapping("/add")
    public ResponseResult addImg(@RequestParam("aId") int aId,
                                 @RequestParam("imgs") String imgs) {
        //调用FastJson的序列化工具，将前端传过来的图片数组序列化字符串再反序列化为Java对象（此处转换为list）
        List<String> imgList = JSONArray.parseArray(imgs, String.class);
        for (String imgUrl:imgList) {
            Img img = new Img();
            img.setAId(aId);
            img.setImgUrl(imgUrl);
            imgService.insertImg(img);
        }
        return ResponseResult.success();
    }

}
