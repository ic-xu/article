package com.article.admin.mvc.feedback.service;


import com.article.admin.mvc.feedback.entity.FeedBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedBackRepository extends MongoRepository<FeedBack, String> {

    List<FeedBack> findAllByFromUserId(String userId);


    List<FeedBack> findAllByToUserId(String userId);


    Page<FeedBack> findAllByStatus(int status, PageRequest pageable);
}
