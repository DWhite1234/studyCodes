package com.atguigu.intercepor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JSONUtils {

    public static boolean isJson(String str) {
        try {
            JSON.parse(str);
            return true;
        }catch (JSONException e){
            return false;
        }
    }
}
