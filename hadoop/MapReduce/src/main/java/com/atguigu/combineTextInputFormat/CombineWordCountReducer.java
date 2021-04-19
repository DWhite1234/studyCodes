package com.atguigu.combineTextInputFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CombineWordCountReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    private final IntWritable keyOut = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum=0;
        for (IntWritable value : values) {
            sum+=value.get();
        }
        keyOut.set(sum);
        context.write(key, keyOut);
    }
}
