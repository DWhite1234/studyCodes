package com.atguigu;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

public class WriteToESOne {
    public static void main(String[] args) throws IOException {
        //获取e客户端构建器
        JestClientFactory jestClientFactory = new JestClientFactory();
        //创建链接地址
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder("http://hadoop104:9200").build();
        //设置es连接地址
        jestClientFactory.setHttpClientConfig(httpClientConfig);
        //获取客户端连接
        JestClient client = jestClientFactory.getObject();

        //构建插入数据对象
        Index index = new Index.Builder(
                "{\n" +
                        "  \"id\":\"1002\",\n" +
                        "  \"movie_name\":\"姜子牙\"\n" +
                        "}"
        ).index("movie_test1").type("_doc").id("1002").build();

        //执行插入操作
        client.execute(index);

        //关闭连接
        client.shutdownClient();
    }
}
