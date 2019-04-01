package com.soft.jianyue.api.controller;

import com.soft.jianyue.api.entity.User;
import com.soft.jianyue.api.entity.dto.UserDTO;
import com.soft.jianyue.api.service.UserService;
import com.soft.jianyue.api.util.MsgConst;
import com.soft.jianyue.api.util.ResponseResult;
import com.soft.jianyue.api.util.StatusConst;
import com.soft.jianyue.api.util.StringUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping(value = "/sign_in")
    public ResponseResult signIn(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        User user = userService.getUserByMobile(userDTO.getMobile());
        if (user == null) {
            return ResponseResult.error(StatusConst.USER_MOBILE_NOT_FOUND, MsgConst.USER_MOBILE_NO_FOUND);
        } else {
            //手机号存在，将明文密码转成Base64密文后进行登录
            userDTO.setPassword(StringUtil.getBCryptEncoder(userDTO.getPassword()));
            int status = userService.signIn(userDTO);
            if (status == StatusConst.SUCCESS) {
                return ResponseResult.success(user);
            } else if (status == StatusConst.PASSWORD_ERROR) {
                return ResponseResult.error(status, MsgConst.PASSWORD_ERROR);
            } else {
                return ResponseResult.error(status, MsgConst.USER_STATUS_ERROR);
            }
        }
    }
}