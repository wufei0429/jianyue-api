package com.soft.jianyue.api.service.impl;

import com.soft.jianyue.api.entity.User;
import com.soft.jianyue.api.entity.dto.UserDTO;
import com.soft.jianyue.api.service.UserService;
import com.soft.jianyue.api.util.StatusConst;
import com.soft.jianyue.api.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Resource
    private UserService userService;

    @Test
    public void getUserByMobile() {
        User user = userService.getUserByMobile("");
        System.out.println(user);
    }

    @Test
    public void signIn() {
        UserDTO loginUser = new UserDTO();
        loginUser.setMobile("17805116182");
        String base64Pass = StringUtil.getBCryptEncoder("123");
//        System.out.println(base64Pass);
        loginUser.setPassword(base64Pass);
        int status = userService.signIn(loginUser);
//        System.out.println(status);
        assertEquals(StatusConst.SUCCESS, status);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setMobile("17805116182");
        String password = StringUtil.getBCryptEncoder("123");
        user.setPassword(password);
        user.setNickname("刘");
        user.setAvatar("511.jpg");
        userService.insert(user);
    }
}