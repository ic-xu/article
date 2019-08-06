package com.common.mvc.qa.service;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.qa.entity.QuestContent;

public interface QuestContentRepository extends MongoRepository<QuestContent, String> {


    public QuestContent findByQuestContentId(String id);
}
