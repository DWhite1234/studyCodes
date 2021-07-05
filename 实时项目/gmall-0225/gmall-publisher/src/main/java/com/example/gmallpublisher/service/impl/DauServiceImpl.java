package com.example.gmallpublisher.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.gmallpublisher.mapper.DauMapper;
import com.example.gmallpublisher.service.DauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DauServiceImpl implements DauService {

    @Autowired
    DauMapper dauMapper;

    @Override
    public String queryOneDayCount(String date) {
        Integer total = dauMapper.selectDauTotal(date);

        //2.创建map集合用来存放新增日活数据
        HashMap<String, Object> dauMap = new HashMap<>();

        //3.创建map集合用来存放新增设备数据
        HashMap<String, Object> devMap = new HashMap<>();

        //4.将数据封装到Map集合中
        dauMap.put("id", "dau");
        dauMap.put("name", "新增日活");
        dauMap.put("value", total);

        devMap.put("id", "new_mid");
        devMap.put("name", "新增设备");
        devMap.put("value", 233);

        //5.创建list集合用来存放结果数据
        ArrayList<Map> result = new ArrayList<>();

        //6.将封装好的map集合放入存放结果数据的list集合中
        result.add(dauMap);
        result.add(devMap);

        return JSONObject.toJSONString(result);
    }

    @Override
    public Map queryOneHourCount(String date) {

        //1.获取数据
        List<Map> list = dauMapper.selectDauTotalHourMap(date);

        //1.2创建map集合用来存放新的数据
        HashMap<String, Long> hourMap = new HashMap<>();

        //2.遍历list集合
        for (Map map : list) {
            hourMap.put((String) map.get("LH"), (Long) map.get("CT"));
        }
        return hourMap;
    }
}
