package com.example.gmallpublisher.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface DauService {
    public String queryOneDayCount(String date);

    public Map queryOneHourCount(String date);
}
