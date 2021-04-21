package com.atguigu.reduceJoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class reduceJoinReducer extends Reducer<Text,ReduceJoinBean,ReduceJoinBean, NullWritable> {

    private ReduceJoinBean bean = new ReduceJoinBean();
    private ArrayList<ReduceJoinBean> list;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        list = new ArrayList();
    }

    @Override
    protected void reduce(Text key, Iterable<ReduceJoinBean> values, Context context) throws IOException, InterruptedException {
        list.clear();
        for (ReduceJoinBean value : values) {
            if ("order".equals(value.getFlag())) {
                ReduceJoinBean tmp = new ReduceJoinBean();
                try {
                    BeanUtils.copyProperties(tmp, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.add(tmp);
            }else{
                try {
                    BeanUtils.copyProperties(bean, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for (ReduceJoinBean joinBean : list) {
            joinBean.setPname(bean.getPname());
            System.out.println("outkey"+joinBean);
            context.write(joinBean,NullWritable.get());
        }
    }
}
