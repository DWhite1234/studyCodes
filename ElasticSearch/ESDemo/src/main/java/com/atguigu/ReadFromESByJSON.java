package com.atguigu;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReadFromESByJSON {
    public static void main(String[] args) throws IOException {
        //获取构建起
        JestClientFactory jestClientFactory = new JestClientFactory();

        //获取连接地址
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder("http://hadoop104:9200").build();

        //设置连接地址
        jestClientFactory.setHttpClientConfig(httpClientConfig);

        //获取客户端对象
        JestClient client = jestClientFactory.getObject();

        Search search = new Search.Builder(
                "{\n" +
                        "   \"query\": {\n" +
                        "    \"bool\":{\n" +
                        "      \"must\": [\n" +
                        "        {\n" +
                        "          \"match\": {\n" +
                        "            \"favo\": \"篮球\"\n" +
                        "          }\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"
        )
                .addIndex("student")
                .addType("_doc")
                .build();

        SearchResult result = client.execute(search);

        System.out.println("查询条数:" + result.getTotal());

        List<SearchResult.Hit<Map, Void>> hits = result.getHits(Map.class);
        for (SearchResult.Hit<Map, Void> hit : hits) {
            Map source = hit.source;
            for (Object o : source.keySet()) {
                System.out.println("source " + source.get(o));
            }
        }
        client.shutdownClient();
    }
}
