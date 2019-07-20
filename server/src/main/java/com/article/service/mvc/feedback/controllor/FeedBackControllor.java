package com.article.service.mvc.feedback.controllor;

import com.article.service.utils.BaseResponseDto;
import com.article.service.utils.IdWorker;
import com.article.service.mvc.feedback.entity.FeedBack;
import com.article.service.mvc.feedback.service.FeedBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/getFeedBack")
    @ApiOperation("获取用户的反馈信息")
    public BaseResponseDto getFeedBack(@RequestParam(name = "userId", required = true) String userId,
                                       @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("createTime")));
        Page<FeedBack> allByStatus = feedBackService.findAllByStatus(userId, pageRequest);

        List<FeedBack> allMessage = new ArrayList<>();

        allByStatus.forEach(feedBack -> {
            allMessage.add(feedBack);
            if (feedBack.getStatus() > 0) {
                List<FeedBack> all = feedBackService.findAllByToUserId(userId);
                allMessage.addAll(all);
            }
        });

        return BaseResponseDto.success(allMessage);
    }


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
