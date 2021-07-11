package com.atguigu;

import com.atguigu.bean.Movie;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;

import java.io.IOException;

public class WriteToESBatch {
    public static void main(String[] args) throws IOException {
        //获取构造器
        JestClientFactory jestClientFactory = new JestClientFactory();

        //获取连接地址
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder("http://hadoop104:9200").build();

        //设置连接地址
        jestClientFactory.setHttpClientConfig(httpClientConfig);

        //获取连接对象
        JestClient client = jestClientFactory.getObject();

        //执行对象
        Movie movie1 = new Movie("1001", "神雕侠侣");
        Movie movie2 = new Movie("1002", "天龙八部");
        Index build1 = new Index.Builder(movie1).id("1001").build();
        Index build2 = new Index.Builder(movie2).id("1002").build();

        Bulk build = new Bulk.Builder()
                .defaultIndex("movie_test1")
                .defaultType("_doc")
                .addAction(build1)
                .addAction(build2)
                .build();

        client.execute(build);
        client.shutdownClient();
    }
}
