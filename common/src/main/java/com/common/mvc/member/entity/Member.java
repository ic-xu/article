package com.common.mvc.member.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {


    @Id
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户头像")
    private String userImgSmall;

    @ApiModelProperty("用户喜欢作品数")
    private String likes;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("职业")
    private String job;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户头像")
    private String img;

    @ApiModelProperty("简介")
    private String desc;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private long createTime;

}
