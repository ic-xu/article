package com.common.mvc.explore.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Topics {

    @Id
    @ApiModelProperty("主题名称，不可重复")
    private String TopicNamel;
    @ApiModelProperty("主题显示列表所需要展示的图片")
    private String imgUrl;
}
