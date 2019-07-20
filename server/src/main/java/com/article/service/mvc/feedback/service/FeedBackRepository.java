package com.article.service.mvc.feedback.service;

import com.article.service.mvc.feedback.entity.FeedBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface FeedBackRepository extends MongoRepository<FeedBack, String> {

    Page<FeedBack> findAllByFromUserId(String userId, PageRequest pageRequest);

    List<FeedBack> findAllByToUserId(String userId);
}
