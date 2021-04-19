package com.atguigu.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Arrays;

public class FlowReducer extends Reducer<Text,FlowBean, Text,FlowBean> {
    private FlowBean flow = new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        //设置求和变量
        int sumUp=0;
        int sumDown=0;
        for (FlowBean bean : values) {
            sumUp+=bean.getUpFlow();
            sumDown+=bean.getDownFlow();
        }
        flow.setUpFlow(sumUp);
        flow.setDownFlow(sumDown);
        flow.setSumFlow();
        context.write(key,flow);
    }
}
