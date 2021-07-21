package com.example.gmallpublisher.service.impl;

import com.example.gmallpublisher.mapper.OrderMapper;
import com.example.gmallpublisher.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public Double selectOrderAmountTotal(String date) {
        return orderMapper.selectOrderAmountTotal(date);
    }

    @Override
    public Map selectOrderAmountHourMap(String date) {
        List<Map> list = orderMapper.selectOrderAmountHourMap(date);

        HashMap<String, Double> map = new HashMap<>();

        for (Map m : list) {
            map.put((String)m.get("CREATE_HOUR"), (double)m.get("SUM_AMOUNT"));
        }
        return map;
    }
}
