package com.atguigu.output;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text keyIn;
    private IntWritable keyOut;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");
        for (String word : words) {
            keyIn.set(word);
            context.write(keyIn, keyOut);
        }
    }

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        keyIn=new Text();
        keyOut=new IntWritable(1);
    }

}
