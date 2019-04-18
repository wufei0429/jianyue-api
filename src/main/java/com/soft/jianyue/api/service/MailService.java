package com.soft.jianyue.api.service;

public interface MailService {
    /**
     * 发送简单邮件
     */
    void sendMail(String to,String subject,String content);

}