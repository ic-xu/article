package com.article.service.mvc.msghistory;

import com.common.mvc.msghistory.service.MessageHistoryService;
import com.common.utils.BaseResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "历史聊天消息相关")
@RequestMapping("msg/")
public class MessageHistoryControllor {

    @Autowired
    private MessageHistoryService messageHistoryService;


    /**
     * 获取历史消息记录
     *
     * @param userId er
     * @return BaseResponseDto
     */
    @ApiOperation("获取历史消息记录")
    @PostMapping("json")
    public BaseResponseDto insert(String userId) {
         return BaseResponseDto.success(messageHistoryService.findById(userId));
    }
}
