package com.atguigu.parttitionner;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PartBean implements WritableComparable<PartBean> {
    private Integer upFlow;

    @Override
    public int compareTo(PartBean o) {
        if (this.sumFlow<o.sumFlow) {
            return 1;
        } else if (this.sumFlow>o.sumFlow) {
            return -1;
        }else{
            if (this.upFlow < o.upFlow) {
                return 1;
            } else if (this.upFlow>o.upFlow) {
                return -1;
            } else{
                return 0;
            }
        }
    }

    private Integer downFlow;
    private Integer sumFlow;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(upFlow);
        out.writeInt(downFlow);
        out.writeInt(sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readInt();
        downFlow = in.readInt();
        sumFlow = in.readInt();
    }

    @Override
    public String toString() {
        return upFlow+"\t"+downFlow+"\t"+sumFlow;
    }

    public void setSumFlow() {
        sumFlow = upFlow + downFlow;
    }
    public Integer getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Integer upFlow) {
        this.upFlow = upFlow;
    }

    public Integer getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Integer downFlow) {
        this.downFlow = downFlow;
    }

    public Integer getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Integer sumFlow) {
        this.sumFlow = sumFlow;
    }
}
