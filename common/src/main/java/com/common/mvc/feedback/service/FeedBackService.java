package com.common.mvc.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import com.common.mvc.feedback.entity.FeedBack;

import java.util.List;
import java.util.Optional;

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
     * 根据用户userID查询反馈信息
     *
     * @param userId 用户Id
     * @return
     */
    public List<FeedBack> findAllByFromUserId(String userId) {
        return feedBackRepository.findAllByFromUserId(userId);
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


    /**
     * 后台获取用户反馈列表
     *
     * @param status
     * @return
     */
    public Page<FeedBack> findAll(int status, PageRequest pageable ) {
        if (status < 0)
            return feedBackRepository.findAll(pageable);
        else
            return feedBackRepository.findAllByStatus(status, pageable);
    }


    /**
     * 根据状态查询反馈信息
     *
     * @param id 用户Id
     * @return
     */
    public Optional<FeedBack> findById(String id) {
        return feedBackRepository.findById(id);
    }



}
