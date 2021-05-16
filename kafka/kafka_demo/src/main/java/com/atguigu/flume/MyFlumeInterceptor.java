package com.atguigu.flume;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

public class MyFlumeInterceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte b = event.getBody()[0];
        Map<String, String> headers = event.getHeaders();
        if (b >= '0' && b <= '9') {
            headers.put("topic", "first");
        }
        event.setHeaders(headers);
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class MyFlumeBuilder implements Builder {
        @Override
        public Interceptor build() {
            return new MyFlumeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

}
