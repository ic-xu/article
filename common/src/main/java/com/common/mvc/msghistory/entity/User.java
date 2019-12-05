package com.common.mvc.msghistory.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by chenxu
 * On 19-8-28 下午5:49
 */

@Data
public class User {

    @Id
    private String id;

    @ApiModelProperty("用户名 username")
    private String displayName;

    @ApiModelProperty("用户头像 userImgSmall")
    private String avatar;

}
