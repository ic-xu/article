package com.common.mvc.feedback.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.feedback.entity.FeedBack;

import java.util.List;

public interface FeedBackRepository extends MongoRepository<FeedBack, String> {

    List<FeedBack> findAllByFromUserId(String userId);


    List<FeedBack> findAllByToUserId(String userId);


    Page<FeedBack> findAllByStatus(int status, PageRequest pageable);
}
