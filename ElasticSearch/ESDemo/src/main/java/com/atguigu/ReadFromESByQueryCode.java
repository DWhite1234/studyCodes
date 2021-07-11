package com.atguigu;

import com.sun.org.apache.bcel.internal.generic.NEW;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.Bucket;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReadFromESByQueryCode {
    public static void main(String[] args) throws IOException {
        //获取构造器
        JestClientFactory jestClientFactory = new JestClientFactory();

        //获取连接地址,注意一定不能写成https,否则执行必定报错
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder("http://hadoop104:9200").build();

        //设置连接地址
        jestClientFactory.setHttpClientConfig(httpClientConfig);

        //获取客户端
        JestClient client = jestClientFactory.getObject();

        //查询代码类似于 es的查询语法
        /*
            {
              "query": {
                "bool": {
                  "filter": {
                    "term": {
                      "favo": "篮球 "
                    }
                  },
                  "must": [
                    {
                      "match": {
                        "favo": "篮球"
                      }
                    }
                  ]
                }
              },
              "aggs": {
                "groupByClassId": {
                  "terms": {
                    "field": "class_id",
                    "size": 10
                  },
                  "aggs": {
                    "groupByName": {
                      "terms": {
                        "field": "name",
                        "size": 10
                      }
                    }
                  }
                }
              },
              "from": 0,
              "size":10
            }
         */
        //TODO 构建最外层的{}
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//      TODO 1.构建query层
        //TODO 指定filter的字段
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("name", "赵");
        //TODO 指定must的字段
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("favo", "唱跳");
//      TODO 2.将filter字段和must字段放入bool层
        boolQueryBuilder.filter(termQueryBuilder);
        boolQueryBuilder.must(matchQueryBuilder);
//      TODO 3.将bool层放入query层
        searchSourceBuilder.query(boolQueryBuilder);
//      TODO 4.构建aggs层
        TermsAggregationBuilder groupByClassId = AggregationBuilders.terms("groupByClassId").field("class_id").size(10);
        //TODO  聚合查询的子聚合查询
        TermsAggregationBuilder groupById= AggregationBuilders.terms("groupById").field("stu_id").size(10);
        //TODO 将子聚合查询放入聚合查询
        TermsAggregationBuilder termsAggregationBuilder = groupByClassId.subAggregation(groupById);
        //TODO 将聚合查询terms放入aggs层
        searchSourceBuilder.aggregation(termsAggregationBuilder);
//      TODO 5.指定分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);




//        TODO 准备工作完成,准备开始构建查询
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("student")
                .addType("_doc")
                .build();

        SearchResult result = client.execute(search);

        System.out.println("查询条数:" + result.getTotal());

        //TODO 查询完成,遍历查询结果
        List<SearchResult.Hit<Map, Void>> hits = result.getHits(Map.class);
        //TODO 遍历hits
        for (SearchResult.Hit<Map, Void> hit : hits) {
            //获取hit的key值
            Map source = hit.source;
            //遍历key获取value
            for (Object o : source.keySet()) {
                System.out.println("查询数据:" + source.get(o));
            }
        }

        //todo 读取聚合数据
        MetricAggregation aggregations = result.getAggregations();
        //todo 根据聚合方式返回聚合值
        TermsAggregation groupByClassIdAggres = aggregations.getTermsAggregation("groupByClassId");
        List<TermsAggregation.Entry> buckets = groupByClassIdAggres.getBuckets();
        //todo 遍历聚合值
        for (TermsAggregation.Entry bucket : buckets) {
            System.out.println("bucket.getKey() = " + bucket.getKey());
            TermsAggregation groupByIdAggres = bucket.getTermsAggregation("groupById");
            List<TermsAggregation.Entry>  groupByIdAggresBuckets = groupByIdAggres.getBuckets();
            for (TermsAggregation.Entry groupByIdAggresBucket : groupByIdAggresBuckets) {
                System.out.println("groupByIdAggresBucket.getKey() = " + groupByIdAggresBucket.getKey());
            }
        }

        client.shutdownClient();
    }
}
