package com.atguigu.parttitionner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PartReducer extends Reducer<PartBean,Text,Text,PartBean> {

    private PartBean partBean = new PartBean();

    @Override
    protected void reduce(PartBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value, key);
        }
    }
}
