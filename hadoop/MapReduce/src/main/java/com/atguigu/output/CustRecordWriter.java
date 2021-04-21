package com.atguigu.output;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class CustRecordWriter extends RecordWriter<Text, IntWritable> {

    private Configuration conf;
    private FSDataOutputStream fdos1;
    private FSDataOutputStream fdos2;
    private FileSystem fs;

    public CustRecordWriter(TaskAttemptContext job) throws IOException {
        conf = job.getConfiguration();
        fs = FileSystem.get(conf);
        fdos1 = fs.create(new Path("d:/output/customOutput1"));
        fdos2 = fs.create(new Path("d:/output/customOutput2"));
    }

    @Override
    public void write(Text key, IntWritable value) throws IOException, InterruptedException {
        System.out.println(key.toString());
        System.out.println(value.toString());
        //输出不同的文件
        fdos1.writeBytes(key.toString());
        fdos2.writeBytes(key.toString());
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStreams(fdos2,fdos1);
    }
}
