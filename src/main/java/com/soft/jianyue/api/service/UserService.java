package com.soft.jianyue.api.service;

import com.soft.jianyue.api.entity.User;
import com.soft.jianyue.api.entity.dto.UserDTO;

public interface UserService {

    /**
     * 根据手机号获取用户信息
     *
     * @param mobile
     * @return
     */
    User getUserByMobile(String mobile);

    /**
     * 登录方法
     *
     * @param userDTO
     * @return boolean
     */
    int signIn(UserDTO userDTO);

    void delete(long id);
    void insert(User user);
    User getUserById(Integer id);
    void updateUser(User user);
    int updateNickName(User user);
    int signUp(UserDTO userDTO);
    int updatePassword(User user);
}