package com.common.mvc.article.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import com.common.mvc.user.entity.Member;

import java.util.List;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    private String id;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章描述")
    private String desc;

    @ApiModelProperty(value = "创建时间")
    private Long happenTime;

    @ApiModelProperty(value = "作者")
    private Member auther;

    @ApiModelProperty("关键字")
    private String keyWord;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "是否标注喜欢")
    private boolean like;

    @ApiModelProperty(value = "有多少人喜欢")
    private Long likeCount;

    private String newsImg;

    @ApiModelProperty("分类")
    private String classify;

    @ApiModelProperty("状态")
    private int Status;

    @ApiModelProperty("图片集合")
    private List<String> imageList;

    @ApiModelProperty("是否收藏")
    private boolean isConllection;

    @ApiModelProperty("紧急的，紧迫的")
    private boolean isUrgent;

    @ApiModelProperty("赏金")
    private double reward;

    @ApiModelProperty("阅读数")
    private int readCount;
}
