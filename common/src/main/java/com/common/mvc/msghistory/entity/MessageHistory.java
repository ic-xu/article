package com.common.mvc.msghistory.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chenxu
 * On 19-11-30 下午3:45
 */
@Data
public class MessageHistory {

    @ApiModelProperty("消息ID,为接受消息一方的用户ID")
    private String id;

    @ApiModelProperty("接收消息一方的用户信息")
    private User user;

    @ApiModelProperty("消息体")
    private String messageBMessageHistoryRepositoryody;

    @ApiModelProperty("消息时间")
    private Long happenTime;
}
