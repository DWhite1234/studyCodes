package com.atguigu.sink;

import com.alibaba.fastjson.JSON;
import com.atguigu.bean.Water;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.ArrayList;

public class ESsink {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);

        ArrayList<HttpHost> list = new ArrayList<>();
        list.add(new HttpHost("hadoop104", 9200));
        list.add(new HttpHost("hadoop105", 9200));
        list.add(new HttpHost("hadoop106", 9200));

        SingleOutputStreamOperator<Water> DStream = senv.socketTextStream("hadoop104", 9999)
                .map(new MapFunction<String, Water>() {
                    @Override
                    public Water map(String value) throws Exception {
                        return new Water(value.split(",")[0], Integer.parseInt(value.split(",")[1]));
                    }
                });

        ElasticsearchSink.Builder<Water> waterBuilder = new ElasticsearchSink.Builder<>(list, new ElasticsearchSinkFunction<Water>() {
            @Override
            public void process(Water element, RuntimeContext ctx, RequestIndexer indexer) {
                System.out.println("开始执行......");
                IndexRequest indexRequest = new IndexRequest("es-sink", "_doc", element.getId());
                indexRequest.source(JSON.toJSONString(element), XContentType.JSON);
                indexer.add(indexRequest);
            }
        });
        waterBuilder.setBulkFlushMaxActions(1);
        ElasticsearchSink<Water> elasticsearchSink = waterBuilder.build();

        DStream.print();
        DStream.addSink(elasticsearchSink);


        senv.execute();
    }
}
