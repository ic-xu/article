package com.common.config;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenxu
 */
@Configuration
public class BaseBeanConfig {

    @Bean(name = "closeableHttpClient")
    public CloseableHttpClient getHttpClint() {
        return HttpClients.createDefault();
    }

}
