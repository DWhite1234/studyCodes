package com.atguigu.parttitionner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class PartPartitioner extends Partitioner<PartBean,Text> {
    @Override
    public int getPartition(PartBean partBean, Text text, int numPartitions) {
        String prePhone = text.toString().substring(0, 3);
        System.out.println("prePhone"+prePhone);
        if ("136".equals(prePhone)) {
            return 0;
        } else if ("137".equals(prePhone)) {
            return 1;
        } else if ("138".equals(prePhone)) {
            return 2;
        } else if ("139".equals(prePhone)) {
            return 3;
        } else{
            return 4;
        }
    }
}
