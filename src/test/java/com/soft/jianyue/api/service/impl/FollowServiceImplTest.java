package com.soft.jianyue.api.service.impl;

import com.soft.jianyue.api.entity.Follow;
import com.soft.jianyue.api.mapper.FollowMapper;
import com.soft.jianyue.api.service.FollowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FollowServiceImplTest {
    @Resource
    private FollowMapper followMapper;

    @Test
    public void insertFollow() {
        Follow follow = new Follow();
        follow.setFromUId(2);
        follow.setToUId(3);
        followMapper.insertFollow(follow);
    }
    @Test
    public void getFollow() {
        System.out.println(followMapper.getFollow(2,1));
    }
    @Test
    public void getFollowsByUId() {
        System.out.println(followMapper.getFollowsByUId(2));
    }
    @Test
    public void deleteFollow() {
        followMapper.deleteFollow(2,3);
    }
}