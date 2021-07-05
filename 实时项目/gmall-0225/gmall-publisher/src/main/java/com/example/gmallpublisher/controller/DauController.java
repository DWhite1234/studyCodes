package com.example.gmallpublisher.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.gmallpublisher.service.DauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DauController {

    @Autowired
    DauService dauService;

    @RequestMapping("/realtime-total")
    public String selectDauOneDay(String date) {
       return dauService.queryOneDayCount(date);
    }

    @RequestMapping("realtime-hours")
    public String selectDauHours(String date) {
        //1.创建Map集合用来存放结果数据
        HashMap<String, Object> result = new HashMap<>();

        //2.获取service层处理完的数据
        //2.1获取昨天的日期
        String yesterday = LocalDate.parse(date).plusDays(-1).toString();
        //2.2获取今天的数据
        Map todayMap = dauService.queryOneHourCount(date);
        //2.3获取昨天的数据
        Map yesterdayMap = dauService.queryOneHourCount(yesterday);

        //3.将数据封装到Map集合中
        result.put("yesterday", yesterdayMap);
        result.put("today", todayMap);
        return JSONObject.toJSONString(result);
    }
}
