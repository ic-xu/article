package com.common.mvc.route.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by chenxu
 * On 19-9-4 下午4:57
 */

@Data
public class PushMessage {

    @ApiModelProperty(required = true, value = "消息唯一标示，用于后期统计结果", example = "5646323451515")
    private String id;

    @ApiModelProperty(required = true, value = "按什么类型推送消息,0 推送所有, 1 按语言推送……", example = "1")
    private int messageType;

    @ApiModelProperty(required = true, value = "类型的值", example = "en")
    private String messageTypeContent;

    private PMessage msg;
}
