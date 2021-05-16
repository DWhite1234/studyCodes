package com.atguigu.intercepor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.Iterator;
import java.util.List;

public class FlumeInterceptor implements Interceptor {
    public void initialize() {

    }

    public Event intercept(Event event) {
        byte[] body = event.getBody();
        String log = new String(body);

        if (JSONUtils.isJson(log)) {
            return event;
        } else {
            return null;
        }
    }

    public List<Event> intercept(List<Event> events) {
        Iterator<Event> iterator = events.iterator();

        while (iterator.hasNext()){
            Event next = iterator.next();
            if(intercept(next)==null){
                iterator.remove();
            }
        }
        return events;
    }

    public void close() {

    }

    public static class MyFlumeBuilder implements Builder{
        public Interceptor build() {
            return new FlumeInterceptor();
        }

        public void configure(Context context) {

        }
    }
}
