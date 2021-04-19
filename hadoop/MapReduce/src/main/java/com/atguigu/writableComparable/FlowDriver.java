package com.atguigu.writableComparable;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "xu lie hua");
        //绑定mapper,reducer
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);
        //绑定朱主类
        job.setJarByClass(FlowDriver.class);
        //指定map输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //指定最终输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //指定输入输出路径
        FileInputFormat.setInputPaths(job, new Path("D:\\input\\"));
        FileOutputFormat.setOutputPath(job, new Path("d:/output/fb2"));

        job.waitForCompletion(true);

    }
}
