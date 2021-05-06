package com.atguigu.intercepor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

public class MyInterceptor implements Interceptor {
    public void initialize() {
    }

    /**
     * 处理event
     * @param event
     * @return
     */
    public Event intercept(Event event) {
        //获取event body数据
        byte b = event.getBody()[0];
        //获取头对象,准备添加参数
        Map<String, String> headers = event.getHeaders();
        if (b > '0' && b < '9') {
            headers.put("type", "letter");
        }else{
            headers.put("type", "number");
        }
        //将headers重新放回event
        event.setHeaders(headers);
        return event;
    }

    /**
     * 处理多条event
     * @param events
     * @return
     */
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    /**
     * 关闭
     */
    public void close() {
    }

    public static class MyBuilder implements Builder{

        public Interceptor build() {
            return new MyInterceptor();
        }

        /**
         * 配置属性
         * @param context
         */
        public void configure(Context context) {

        }
    }
}
