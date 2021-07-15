package com.atguigu.sink;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseSink {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        senv.socketTextStream("hadoop104", 9999)
                .addSink(new SinkFunction<String>() {
                    @Override
                    public void invoke(String value, Context context) throws Exception {
                        Configuration configuration = HBaseConfiguration.create();
                        configuration.set("hbase.zookeeper.quorum","hadoop104,hadoop105,hadoop106");
                        Connection conn = ConnectionFactory.createConnection();
                        Table table = conn.getTable(TableName.valueOf("test"));
                        Put put = new Put(Bytes.toBytes("1001"));
//                        put.addColumn(, , )
                    }
                });
        senv.execute();
    }
}
