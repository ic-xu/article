package com.article.service.mvc.feedback;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.common.mvc.feedback.entity.FeedBack;
import com.common.mvc.feedback.service.FeedBackService;
import com.common.utils.BaseResponseDto;
import com.common.utils.IdWorker;


@RestController
@RequestMapping("/feedback")
@Api(tags = "用户反馈信息相关")
public class FeedBackControllor {

    @Autowired
    FeedBackService feedBackService;



    /**
     * 客户端页面获取反馈的信息
     *
     * @return
     */
    @PostMapping("/insertFeedBack")
    @ApiOperation("新增反馈信息")
    public BaseResponseDto insertFeedBack(@RequestParam(name = "userId", required = true) String userId,
                                          @RequestParam(name = "context", defaultValue = "") String context) {

        FeedBack feedBack = new FeedBack();
        feedBack.setCreateTime(System.currentTimeMillis());
        feedBack.setToUserId("admin");
        feedBack.setFromUserId(userId);
        feedBack.setContent(context);
        feedBack.setId(new IdWorker().nextId() + "" + System.currentTimeMillis());
        return BaseResponseDto.success(feedBackService.save(feedBack));
    }


}
