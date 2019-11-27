package com.common.mvc.qa.entity;

import com.common.mvc.member.entity.Member;
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
public class Answer {

    @Id
    @ApiModelProperty("回答ID")
    private Long id;

    @ApiModelProperty("问题ID")
    private String questId;

    @ApiModelProperty
    private String Content;

    @ApiModelProperty("是否有用")
    private String isUse;

    @ApiModelProperty("回答者")
    private Member auther;

    @ApiModelProperty("答复时间")
    private Long happenTime;

    @ApiModelProperty("赞同数")
    private Long agreeCount;
}
