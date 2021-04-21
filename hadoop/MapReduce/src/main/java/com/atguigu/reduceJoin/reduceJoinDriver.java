package com.atguigu.reduceJoin;

import com.atguigu.wordcount.WordCount;
import com.atguigu.wordcount.WordCountMapper;
import com.atguigu.wordcount.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class reduceJoinDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        //1.创建configuration对象
        Configuration conf = new Configuration();
        //2.创建job实例
        Job job = Job.getInstance(conf,"word count");
        //3.绑定当前jar运行的主程序
        job.setJarByClass(reduceJoinDriver.class);
        //4.绑定运行的mapper类和reducer类
        job.setMapperClass(reduceJoinMapper.class);
        job.setReducerClass(reduceJoinReducer.class);
        //5.指定mapper的(k,v)输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(ReduceJoinBean.class);
        //6.指定最终输出的(k,v)输出类型
        job.setOutputKeyClass(ReduceJoinBean.class);
        job.setOutputValueClass(NullWritable.class);

        //7.指定输入输出路径
        FileInputFormat.setInputPaths(job, new Path("D:\\input\\inputtable"));
        FileOutputFormat.setOutputPath(job, new Path("d:/output/ReduceJoin1"));
        //8.提交任务
        job.waitForCompletion(true);
    }
}
