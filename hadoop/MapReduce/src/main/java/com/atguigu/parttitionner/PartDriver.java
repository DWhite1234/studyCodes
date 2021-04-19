package com.atguigu.parttitionner;

import jdk.nashorn.internal.scripts.JO;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class PartDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(PartDriver.class);

        job.setMapperClass(PartMapper.class);
        job.setReducerClass(PartReducer.class);

        job.setMapOutputKeyClass(PartBean.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PartBean.class);

        job.setPartitionerClass(PartPartitioner.class);
        job.setNumReduceTasks(5);

        FileInputFormat.setInputPaths(job, new Path("d:/phones.txt"));
        FileOutputFormat.setOutputPath(job, new Path("d:/output/partA1"));

        job.waitForCompletion(true);
    }
}
