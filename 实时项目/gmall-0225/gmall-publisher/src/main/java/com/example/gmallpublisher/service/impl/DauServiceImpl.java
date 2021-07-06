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
    public Integer queryOneDayCount(String date) {
        return dauMapper.selectDauTotal(date);
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
