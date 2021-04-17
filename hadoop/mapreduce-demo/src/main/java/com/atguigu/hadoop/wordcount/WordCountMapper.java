package com.atguigu.hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 第一件事 继承mapper类
 * 第二件事 想泛型是什么
 *           2对
 *           一对输入
 *                    keyin       记录当前读取的位置 偏移量 LongWritable
 *                    valuein     读取一行数据              Text
 *           一对输出
 *                   keyout      单词                      Text
 *                   valueout    1                         IntWritable
 *          Context context  全局上下文对象
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    private Text outK;
    private IntWritable outV;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        outK=new Text();
        outV=new IntWritable(1);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.转化数据为String
        //atguigu atguigu
        String line = value.toString();
        //2.切分字符串
        //[atguigu,atguigu]
        String[] words = line.split(" ");
        //使用for循环写出
        for (String word : words) {
            outK.set(word);
           context.write(outK,outV);
        }
    }
}
