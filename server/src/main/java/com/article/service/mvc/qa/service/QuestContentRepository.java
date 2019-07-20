package com.article.service.mvc.qa.service;


import com.article.service.mvc.qa.entity.QuestContent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestContentRepository extends MongoRepository<QuestContent, String> {


    public QuestContent findByQuestContentId(String id);
}
