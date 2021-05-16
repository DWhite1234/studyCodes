package com.atguigu.intercepor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

public class FlumeInterceptorAfter implements Interceptor {
    public void initialize() {

    }

    public Event intercept(Event event) {
        Map<String, String> headers = event.getHeaders();
        String log = new String(event.getBody());

        JSONObject jsonObject = JSONObject.parseObject(log);
        String ts = jsonObject.getString("ts");
        headers.put("timestamp", ts);
        return event;

    }

    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    public void close() {

    }

    public static class MyFlumeAfterBuilder implements Builder{

        public Interceptor build() {
            return new FlumeInterceptorAfter();
        }

        public void configure(Context context) {

        }
    }
}
