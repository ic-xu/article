package com.common.mvc.qa.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Quest {

    @Id
    @ApiModelProperty("id")
    private String questId;

//    @ApiModelProperty("是否是热门话题")
//    private Boolean isHot;

    @ApiModelProperty("问题分类")
    private String questClassify;

    @ApiModelProperty("问题提问者")
    private String UserName;

    @ApiModelProperty("问题描述")
    private String describe;

//    @ApiModelProperty("是否收藏")
//    private Boolean isConllection;

    @ApiModelProperty
    private Long happenTIme;

    @ApiModelProperty("问题赏金")
    private Double reWard;

    @ApiModelProperty("问题详细描述")
    private String Content;

    @ApiModelProperty("回答数")
    private Integer answerCount;

    @ApiModelProperty("观看数")
    private Long readCount;

}
