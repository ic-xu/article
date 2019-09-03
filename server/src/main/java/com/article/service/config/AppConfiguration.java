package com.article.service.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ Date 19-7-4 - 下午3:43
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppConfiguration {

    @Value("${app.zk.root}")
    private String zkRoot;

    @Value("${app.zk.addr}")
    private String zkAddr;


    @Value("${server.port}")
    private int port;

    @Value("${app.zk.connect.timeout}")
    private int zkConnectTimeout;

//    @Value("${app.route.way.interface}")
//    private String routeWay;

//    @Value("${app.route.way.consitenthash}")
//    private String consistentHashWay;
}
