package com.soft.jianyue.api.task;

import com.soft.jianyue.api.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
//@Async
public class TaskService {
    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 50 9 ? * MON")
    public void proces(){
        mailService.sendMail("16422802@qq.com","吴飞","：老师好 9：50");
        System.out.println("111");
    }
}