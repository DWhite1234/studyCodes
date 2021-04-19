package com.atguigu.combineTextInputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class CombineWordCount {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        //1.创建configuration对象
        Configuration conf = new Configuration();
        //2.创建job实例
        Job job = Job.getInstance(conf,"Combine Textcount");
//        //3.绑定当前jar运行的主程序
        job.setJarByClass(CombineWordCount.class);
////        //4.绑定运行的mapper类和reducer类
        job.setMapperClass(CombineWordCountMapper.class);
        job.setReducerClass(CombineWordCountReducer.class);
//        //5.指定mapper的(k,v)输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //6.指定最终输出的(k,v)输出类型
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
//
//        //指定切片实现类
        job.setInputFormatClass(CombineTextInputFormat.class);
//        //指定虚拟存储大小(类似切片大小)
        CombineTextInputFormat.setMaxInputSplitSize(job, 4193304);
//
        //7.指定输入输出路径
        FileInputFormat.setInputPaths(job, new Path("E:\\0225\\04_尚硅谷大数据技术之Hadoop\\2.资料\\09_测试数据\\input\\inputcombinetextinputformat"));
        FileOutputFormat.setOutputPath(job, new Path("d:/output/combineTextOutput6"));
//        //8.提交任务
        job.waitForCompletion(true);
    }
}
