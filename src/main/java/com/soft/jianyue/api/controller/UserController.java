package com.soft.jianyue.api.controller;

import com.aliyun.oss.OSSClient;
import com.soft.jianyue.api.entity.User;
import com.soft.jianyue.api.entity.dto.UserDTO;
import com.soft.jianyue.api.service.UserService;
import com.soft.jianyue.api.service.RedisService;
import com.soft.jianyue.api.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    private UserService userService;
    private RedisService redisService;

    @RequestMapping(value = "/select/{mobile}",method = RequestMethod.GET)
    public User getUserByMobile(@PathVariable("mobile") String mobile){
        return userService.getUserByMobile(mobile);
    }
    @PostMapping(value = "/sign_in")
    public ResponseResult signIn(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        User user = userService.getUserByMobile(userDTO.getMobile());
        if (user == null) {
            return ResponseResult.error(StatusConst.USER_MOBILE_NOT_FOUND, MsgConst.USER_MOBILE_NO_FOUND);
        } else {
            //手机号存在，将明文密码转成Base64密文后进行登录
            userDTO.setPassword(StringUtil.getBase64Encoder (userDTO.getPassword()));
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
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable("id") long id){
        userService.delete(id);
    }
    @RequestMapping(value = "/inset", method = RequestMethod.POST)
    public void addCourse(@RequestBody User user) {
        userService.insert(user);
    }


    @PostMapping("/avatar")
    public String ossUpload(@RequestParam("file") MultipartFile sourceFile, @RequestParam("userId") int userId) {
        System.out.println(userId);
        String endpoint = "oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAICzOkftRRa60T";
        String accessKeySecret = "DrMGNFPkCzbTJN0OoV9hkW7dmTjLf4";
        String bucketName = "niit-soft-huo";
        String filedir = "avatar/";
        // 获取文件名
        String fileName = sourceFile.getOriginalFilename();
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //uuid生成主文件名
        String prefix = UUID.randomUUID().toString();
        String newFileName = prefix + suffix;
        File tempFile = null;
        try {
            //创建临时文件
            tempFile = File.createTempFile(prefix, prefix);
            // MultipartFile to File
            sourceFile.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, filedir + newFileName, tempFile);
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, filedir + newFileName, expiration);
        ossClient.shutdown();
        User user = userService.getUserById(userId);
        user.setAvatar(url.toString());
        userService.updateUser(user);
        return url.toString();
    }


    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updatePassword(@RequestParam("password") String password, @RequestParam("id") Integer id) {
        User user = userService.getUserById(id);
        String base64Pass = StringUtil.getBase64Encoder(password);
        user.setPassword(base64Pass);
        int a = userService.updatePassword(user);
        ResponseResult rb = new ResponseResult();
        rb.setCode(0);
        rb.setMsg("");
        rb.setData("");
        if (a > 0) {
            return rb;
        }
        return null;
    }

    @RequestMapping(value = "/updateNickName", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateNickName(@RequestParam("nickname") String nickname, @RequestParam("id") Integer id) {
        User user = userService.getUserById(id);
        user.setNickname(nickname);
        int a = userService.updateNickName(user);
        ResponseResult rb = new ResponseResult();
        rb.setCode(0);
        rb.setMsg("");
        rb.setData("");
        if (a > 0) {
            return rb;
        }
        return null;
    }


    /*获取短信验证码*/
    @PostMapping(value = "/verify")
    public ResponseResult getVerifyCode(@RequestParam("mobile") String mobile) {
        User user = userService.getUserByMobile(mobile);
        if (user != null) {
            return ResponseResult.error(StatusConst.MOBILE_EXIST, MsgConst.MOBILE_EXIST);
        } else {
  //          String verifyCode = SMSUtil.send(mobile);
            String verifyCode = StringUtil.getVerifyCode();
            System.out.println(verifyCode);
            redisService.set(mobile, verifyCode);
            return ResponseResult.success();
        }
    }


    @PostMapping(value = "/check")
    public ResponseResult checkVerifyCode(@RequestParam("mobile") String mobile, @RequestParam("verifyCode") String verifyCode) {
        //从Redis中取出这个手机号的验证码
        String code = redisService.get(mobile).toString();
        //System.out.println(code + "---");
        // System.out.println(verifyCode);
        //和客户端传过来的验证码比对
        if (code.equals(verifyCode)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error(StatusConst.VERIFYCODE_ERROR, MsgConst.VERIFYCODE_ERROR);
        }
    }
    @PostMapping(value = "/sign_up")
    public ResponseResult signUp(@RequestBody UserDTO userDTO) {
        userService.signUp(userDTO);
        return ResponseResult.success();
    }
}