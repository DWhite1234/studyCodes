package com.atguigu.hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 第一件事    继承redcuer类
 * 第二件事   思考泛型
 *            两对
 *                一对输入
 *                          keyin       单词
 *                          valuein     1
 *                一对输出
 *                          keyout     统计后的单词
 *                          valueout   统计后的结果
 *
 */
public class WordCountReducer extends Reducer<Text, IntWritable,Text,IntWritable>{
    private IntWritable outV=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //定义求和变量
        int sum=0;
        //1.atguigu,[1,1]
        for (IntWritable value : values) {
             sum+=value.get();
        }
        //把当前sum做封装
        outV.set(sum);
        //把最终结果写出
        context.write(key,outV);
    }
}
