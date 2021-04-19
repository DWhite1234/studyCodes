package com.atguigu.writableComparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text,Text, FlowBean> {
    private FlowBean flow = new FlowBean();
    private Text outk = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        flow.setUpFlow(Integer.parseInt(split[split.length-3]));
        flow.setDownFlow(Integer.parseInt(split[split.length-2]));
        flow.setSumFlow();
        outk.set(split[1]);
        System.out.println(flow);
        context.write(outk,flow);
    }
}
