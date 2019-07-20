package com.article.service.mvc.about.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class About {

    @Id
    private String id = "about";

    @ApiModelProperty("富文本页面")
    private String content;

}
