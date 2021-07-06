package com.example.gmallpublisher.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Double selectOrderAmountTotal(String date);

    Map selectOrderAmountHourMap(String date);
}
