package com.article.service.mvc.community.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Likes {

    @Id
    private String id;

    @ApiModelProperty(value = "userId",required = true)
    private String userId;

    @ApiModelProperty(value = "article")
    private String articleId;

}
