package com.atguigu.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FixedLengthInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        //1.创建configuration对象
        Configuration conf = new Configuration();

        //开启map的压缩输出
        conf.set("mapreduce.map.output.compress", "true");
        conf.set("mapreduce.map.output.compress.codec", "org.apache.hadoop.io.compress.BZip2Codec");

        //开启reduce的压缩输出
        conf.set("mapreduce.output.fileoutputformat.compress", "true");
        conf.set("mapreduce.output.fileoutputformat.compress.codec", "org.apache.hadoop.io.compress.BZip2Codec");
        //2.创建job实例
        Job job = Job.getInstance(conf,"word count");
        //3.绑定当前jar运行的主程序
        job.setJarByClass(WordCount.class);
        //4.绑定运行的mapper类和reducer类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        //5.指定mapper的(k,v)输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //6.指定最终输出的(k,v)输出类型
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setCombinerClass(WordCountReducer.class);
        //7.指定输入输出路径
        FileInputFormat.setInputPaths(job, new Path("D:\\input\\word.txt.bz2"));
        FileOutputFormat.setOutputPath(job, new Path("d:/output/TextOutput2"));
        //8.提交任务
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
