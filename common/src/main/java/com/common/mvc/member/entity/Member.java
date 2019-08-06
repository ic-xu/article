package com.common.mvc.member.entity;

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
public class Member {

    @Id
    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户头像")
    private String userImgSmall;

    @ApiModelProperty("用户喜欢作品数")
    private String likes;

    @ApiModelProperty("用户头像")
    private String phone;

    @ApiModelProperty("用户头像")
    private String job;

    @ApiModelProperty("用户头像")
    private String password;

    @ApiModelProperty("用户头像")
    private String img;

    @ApiModelProperty("用户头像")
    private String desc;

    @ApiModelProperty("用户头像")
    private long createTime;

}
