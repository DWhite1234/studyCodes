package com.example.gmallpublisher.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.gmallpublisher.service.DauService;
import com.example.gmallpublisher.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DauController {

    @Autowired
    DauService dauService;

    @Autowired
    OrderService orderService;

    @RequestMapping("/realtime-total")
    public String selectDauOneDay(String date) {
        Integer total = dauService.queryOneDayCount(date);

        Double amountTotal = orderService.selectOrderAmountTotal(date);

        //2.创建map集合用来存放新增日活数据
        HashMap<String, Object> dauMap = new HashMap<>();

        //3.创建map集合用来存放新增设备数据
        HashMap<String, Object> devMap = new HashMap<>();

        //4.创建map,存放每日新增交易额
        HashMap<String, Object> orderMap = new HashMap<>();

        //4.将数据封装到Map集合中
        dauMap.put("id", "dau");
        dauMap.put("name", "新增日活");
        dauMap.put("value", total);

        devMap.put("id", "new_mid");
        devMap.put("name", "新增设备");
        devMap.put("value", 233);

        orderMap.put("id", "order_amount");
        orderMap.put("name", "新增交易额");
        orderMap.put("value", amountTotal);

        //5.创建list集合用来存放结果数据
        ArrayList<Map> result = new ArrayList<>();

        //6.将封装好的map集合放入存放结果数据的list集合中
        result.add(dauMap);
        result.add(devMap);
        result.add(orderMap);

        return JSONObject.toJSONString(result);
    }

    @RequestMapping("realtime-hours")
    public String selectDauHours(String id,String date) {
        //获取昨天的日期
        String yesterday = LocalDate.parse(date).plusDays(-1).toString();
        Map todayHourMap = null;
        Map yesterdayHourMap = null;

        if ("dau".equals(id)) {

            //获取今天日活数据
            todayHourMap = dauService.queryOneHourCount(date);

            //获取昨天数据
            yesterdayHourMap = dauService.queryOneHourCount(yesterday);
        } else if ("order_amount".equals(id)) {
            //获取今天交易额数据
            todayHourMap = orderService.selectOrderAmountHourMap(date);

            yesterdayHourMap = orderService.selectOrderAmountHourMap(yesterday);
        }

        //创建map集合用于存放结果数据
        HashMap<String, Object> result = new HashMap<>();

        result.put("yesterday", yesterdayHourMap);
        result.put("today", todayHourMap);

        return JSONObject.toJSONString(result);

    }
}
