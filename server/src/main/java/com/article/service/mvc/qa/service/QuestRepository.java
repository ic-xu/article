package com.article.service.mvc.qa.service;


import com.article.service.mvc.qa.entity.Quest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestRepository extends MongoRepository<Quest, String> {

    Quest findByQuestId(String id);
}
