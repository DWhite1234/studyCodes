package com.atguigu.mapJoin;

import jdk.internal.util.xml.impl.ReaderUTF8;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

public class MapJoinMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    private HashMap<String,String> map = new HashMap();
    private FSDataInputStream fdis;
    private BufferedReader reader;
    private Text outk = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        URI cacheFile = cacheFiles[0];
        FileSystem fs = FileSystem.get(context.getConfiguration());
        fdis = fs.open(new Path(cacheFile));
        reader = new BufferedReader(new ReaderUTF8(fdis));
        String line ;
        while ((line= reader.readLine())!=null){
            String[] s = line.split("\t");
            map.put(s[0],s[1]);
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        context.getCounter("mucount", "MapCount").increment(1);
        String[] split = value.toString().split("\t");
        outk.set(split[0]+"\t"+map.get(split[1])+"\t"+split[2]);
        context.write(outk,NullWritable.get());

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        IOUtils.closeStreams(reader,fdis);
    }
}
