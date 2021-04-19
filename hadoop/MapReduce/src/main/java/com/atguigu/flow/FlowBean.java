package com.atguigu.flow;


import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements Writable {
    private Integer upFlow;
    private Integer downFlow;
    private Integer sumFlow;

    public FlowBean() {
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(upFlow);
        dataOutput.writeInt(downFlow);
        dataOutput.writeInt(sumFlow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
         upFlow = dataInput.readInt();
         downFlow = dataInput.readInt();
         sumFlow = dataInput.readInt();
    }

    @Override
    public String toString() {
        return upFlow+"\t"+downFlow+"\t"+sumFlow;
    }

    public void setSumFlow() {
        this.sumFlow=this.upFlow+this.downFlow;
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
