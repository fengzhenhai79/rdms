package com.gdry.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * elasticsearch 客户端
 */
@Configuration
public class RestHighLevelClientBuilder {


    @Bean
    public RestHighLevelClient elasticsearchClient() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.1.46", 9200, "http")));
        return client;

    }
}
