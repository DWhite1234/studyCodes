package com.example.gmallpublisher.mapper;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    //查所有
    Double selectOrderAmountTotal(String date);

    //查分时
    List<Map> selectOrderAmountHourMap(String date);

}
