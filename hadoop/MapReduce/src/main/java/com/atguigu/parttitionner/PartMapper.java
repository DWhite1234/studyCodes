package com.atguigu.parttitionner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PartMapper extends Mapper<LongWritable, Text,PartBean,Text> {

    private Text outk = new Text();
    private PartBean outv = new PartBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        outv.setUpFlow(Integer.parseInt(split[1]));
        outv.setDownFlow(Integer.parseInt(split[2]));
        outv.setSumFlow();
        outk.set(split[0]);
        context.write(outv,outk );
    }

    //    @Override
//    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        String[] split = value.toString().split("\t");
//        outv.setUpFlow(Integer.parseInt(split[1]));
//        outv.setDownFlow(Integer.parseInt(split[2]));
//        outv.setSumFlow();
//        outk.set(split[0]);
//        context.write(outk,outv );
//    }
}
