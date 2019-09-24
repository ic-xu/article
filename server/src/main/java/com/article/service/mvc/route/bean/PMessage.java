package com.article.service.mvc.route.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PMessage {
    @ApiModelProperty(required = true, value = "标题", example = "hello")
    private String title;

    @ApiModelProperty(required = true, value = "消息内容体，html格式", example = "<!DOCTYPE html>\n<html>\n    <head>\n        <title>这是个标题</title>\n    </head>\n    <body>\n        <h1>这是一个一个简单的HTML</h1>\n        <p>Hello World！</p>\n    </body>\n</html>")
    private String content;

    @ApiModelProperty(required = true, value = "时间戳")
    private long time;

    @ApiModelProperty(required = true, value = "通知栏图标", example = "hello")
    private String logo;

}