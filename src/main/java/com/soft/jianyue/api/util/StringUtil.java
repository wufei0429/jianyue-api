package com.soft.jianyue.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtil {
    public static String getDateString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
    public static String getBCryptEncoder(String srcString) {
        String resultStr = "";
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
            resultStr = encoder.encode(srcString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }
    public static String getUUIDString() {
        return UUID.randomUUID().toString();
    }
}
