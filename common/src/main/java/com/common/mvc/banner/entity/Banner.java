package com.common.mvc.banner.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Banner {

    @Id
    private long id;

    @ApiModelProperty("图片点击地址")
    private String url;

    @ApiModelProperty("图片路径")
    private String imagePath;

    @ApiModelProperty("图片显示标题")
    private String title;

    @ApiModelProperty("图片状态")
    private int status;

}
