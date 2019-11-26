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

    @ApiModelProperty("是否是热门话题")
    private boolean isHot;

    @ApiModelProperty("问题分类")
    private String questClassify;

    @ApiModelProperty("问题提问者")
    private String UserName;

    @ApiModelProperty("问题描述")
    private String describe;


    @ApiModelProperty("是否收藏")
    private boolean isConllection;

    @ApiModelProperty
    private long happenTIme;

    @ApiModelProperty("问题赏金")
    private double reWard;

    @ApiModelProperty
    private String Content;

}
