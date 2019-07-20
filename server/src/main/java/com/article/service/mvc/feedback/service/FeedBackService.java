package com.article.service.mvc.feedback.service;

import com.article.service.mvc.feedback.entity.FeedBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FeedBackService {

    @Autowired
    FeedBackRepository feedBackRepository;


    /**
     * 保存或者修改
     *
     * @param feedBack
     * @return
     */
    public FeedBack save(FeedBack feedBack) {
        return feedBackRepository.save(feedBack);
    }


    /**
     * 根据状态查询反馈信息
     *
     * @param userId 用户Id
     * @return
     */
    public Page<FeedBack> findAllByStatus(String userId, PageRequest pageable) {
        return feedBackRepository.findAllByFromUserId(userId,pageable);
    }


    /**
     * 根据用户userID查询反馈信息
     *
     * @param userId 用户Id
     * @return
     */
    public List<FeedBack> findAllByToUserId(String userId) {
        return feedBackRepository.findAllByToUserId(userId);
    }


}
