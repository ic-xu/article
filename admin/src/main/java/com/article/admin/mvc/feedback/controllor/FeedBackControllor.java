package com.article.admin.mvc.feedback.controllor;

import com.article.admin.utils.BaseResponseDto;
import com.article.admin.utils.IdWorker;
import com.article.admin.mvc.feedback.entity.FeedBack;
import com.article.admin.mvc.feedback.service.FeedBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedback")
@Api(tags = "用户反馈信息相关")
public class FeedBackControllor {

    @Autowired
    FeedBackService feedBackService;


//    /**
//     * 客户端页面获取反馈的信息
//     *
//     * @return
//     */
//    @GetMapping("/getAllFeedBack")
//    @ApiOperation("后台获取所有反馈列表")
//    @ApiImplicitParam(name = "status", value = "获取反馈列表，值为-1表示获取所有，" +
//            "值为0表示获取还未回复的反馈，1表示已回复的反馈", required = true)
//    public BaseResponseDto getAllFeedBack(@RequestParam(defaultValue = "-1", name = "status") int status,
//                                          @RequestParam(defaultValue = "20", name = "pageSize") int pageSize,
//                                          @RequestParam(defaultValue = "0", name = "pageNumber") int pageNumber) {
//        if (pageNumber < 0)
//            pageNumber = 0;
//        if (pageSize < 0)
//            pageSize = 20;
//        return BaseResponseDto.success(feedBackService.findAll(status, pageSize, pageNumber));
//    }


    /**
     * 客户端页面获取反馈的信息
     *
     * @return
     */
    @GetMapping("/getFeedBackAll")
    @ApiOperation("后台获取所有反馈列表")
    @ApiImplicitParam(name = "status", value = "获取某个用户的反馈信息,status 为-1 表示获取所有记录， 0 表示" +
            "没有回复的记录，1表示已经回复反馈了的记录", required = true)
    public BaseResponseDto getFeedBackAll(@RequestParam(defaultValue = "-1", name = "status") int status,
                                          @RequestParam(defaultValue = "20", name = "pageSize") int pageSize,
                                          @RequestParam(defaultValue = "0", name = "pageNumber") int pageNumber) {
        if (pageNumber < 0)
            pageNumber = 0;
        if (pageSize < 0)
            pageSize = 20;
        PageRequest pageRequest = new PageRequest(pageNumber,pageSize,new Sort(Sort.Order.desc("createTime")));

        return BaseResponseDto.success(feedBackService.findAll(status, pageRequest));
    }


    /**
     * 客户端页面获取反馈的信息
     *
     * @return
     */
    @PostMapping("/getAllByUserId")
    @ApiOperation("获取单个用户的反馈记录")
    @ApiImplicitParam(name = "userId", value = "获取某个用户的反馈信息", required = true)
    public BaseResponseDto getAllByUserId(String userId) {
        List<FeedBack> allByStatus = feedBackService.findAllByFromUserId(userId);

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
    @ApiOperation("回复反馈信息")
    public BaseResponseDto insertFeedBack(@RequestParam(name = "id", required = true) String id,
                                          @RequestParam(name = "context", defaultValue = "") String context) {

        //先跟新反馈的状态
        Optional<FeedBack> feedB = feedBackService.findById(id);
        FeedBack feedBack1 = feedB.get();
        feedBack1.setStatus(1);
        feedBackService.save(feedBack1);


        FeedBack feedBack = new FeedBack();
        feedBack.setCreateTime(System.currentTimeMillis());
        feedBack.setToUserId(feedBack1.getFromUserId());
        feedBack.setFromUserId("admin");
        feedBack.setContent(context);
        feedBack.setStatus(1);
        feedBack.setId(new IdWorker().nextId() + "" + System.currentTimeMillis());
        return BaseResponseDto.success(feedBackService.save(feedBack));
    }


}
