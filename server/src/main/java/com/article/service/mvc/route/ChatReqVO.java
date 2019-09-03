package com.article.service.mvc.route;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Function: Google Protocol 编解码发送
 *
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatReqVO {


    @ApiModelProperty(required=false, value="唯一请求号", example = "1234567890")
    private String reqNo;

    @ApiModelProperty(required=false, value="当前请求的时间戳", example = "0")
    private int timeStamp;


    @Id
    @NotNull(message = "消息的唯一ID")
    private Long id;

    @NotNull(message = "toUserId 不能为空")
    @ApiModelProperty(required = true, value = "toUserId", example = "1545574049323")
    private Long toUserId ;

    @NotNull(message = "fromUserId 不能为空")
    @ApiModelProperty(required = true, value = "fromUserId", example = "1545574049323")
    private Long fromUserId;


    @NotNull(message = "msg 不能为空")
    @ApiModelProperty(required = true, value = "msg", example = "hello")
    private String msg ;


}
