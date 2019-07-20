package com.article.admin.mvc.article.entity;

import com.article.admin.mvc.user.entity.Member;
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
public class Article {

    @Id
    private String articleId;
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
    @ApiModelProperty(value = "文章描述")
    private String articleDescribe;
    @ApiModelProperty(value = "创建时间")
    private Long happenTime;

    @ApiModelProperty(value = "审核时间")
    private Long statusChangeTime;

    @ApiModelProperty(value = "内容，一般为null")
    private String content;

    private Member member;

    @ApiModelProperty("关键字")
    private String keyWord;
    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "是否标注喜欢")
    private boolean like;

    private String newsImg;
    @ApiModelProperty("分类")
    private String classify;
    @ApiModelProperty("状态")
    private int Status;

}
