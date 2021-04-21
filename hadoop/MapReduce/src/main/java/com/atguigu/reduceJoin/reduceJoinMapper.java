package com.atguigu.reduceJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class reduceJoinMapper extends Mapper<LongWritable, Text, Text,ReduceJoinBean> {
    private ReduceJoinBean bean = new ReduceJoinBean();
    private Text outkey = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        FileSplit split = (FileSplit) context.getInputSplit();
        String name = split.getPath().getName();
        String[] splits = value.toString().split("\t");
        if ("order.txt".equals(name)) {
            bean.setId(splits[0]);
            bean.setPid(splits[1]);
            bean.setNums(Integer.parseInt(splits[2]));
            bean.setPname("");
            bean.setFlag("order");
            outkey.set(splits[1]);
            context.write(outkey,bean);
        }else{
            bean.setId("");
            bean.setPid(splits[0]);
            bean.setNums(0);
            bean.setPname(splits[1]);
            bean.setFlag("pd");
            outkey.set(splits[0]);
            context.write(outkey, bean);
        }

    }
}
