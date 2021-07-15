package com.atguigu.sink;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLSink {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        senv.socketTextStream("hadoop104", 9999)
                .addSink(JdbcSink.sink(
                        "insert into stu values(?,?,?)"
                        ,
                        new JdbcStatementBuilder<String>() {
                            @Override
                            public void accept(PreparedStatement ps, String s) throws SQLException {
                                ps.setString(1, s.split(",")[0]);
                                ps.setString(2, s.split(",")[1]);
                                ps.setString(3, s.split(",")[2]);
                            }
                        },
//                        设置刷写缓存,测试时使用,不然写不进去数据
                        JdbcExecutionOptions.builder().withBatchSize(1).build()
                        ,
                        new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                                .withUrl("jdbc:mysql://hadoop104:3306/test?useSSL=false")
                                .withUsername("root")
                                .withPassword("123456")
                                .withDriverName("com.mysql.jdbc.Driver")
                                .build()
                ));

        senv.execute();
    }
}
