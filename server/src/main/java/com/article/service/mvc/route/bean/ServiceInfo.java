package com.article.service.mvc.route.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by chenxu
 * On 19-7-18 下午8:18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceInfo {

    private String outIp;

    private int httpPort;

    private int nettyPort;

    private String inIp;

}
