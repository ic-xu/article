package com.article.admin.mvc.article.entity;

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
public class ArticleContent {

    @Id
    private String articleId;
    @ApiModelProperty(value = "文章内容")
    private String content;
}
