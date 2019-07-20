package com.article.service.mvc.community.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class Tabs {

    @Id
    @ApiModelProperty("分类名称")
    private String tabName;

}