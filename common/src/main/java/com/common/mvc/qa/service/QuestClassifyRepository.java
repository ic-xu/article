package com.common.mvc.qa.service;


import com.common.mvc.qa.entity.QuestClassify;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestClassifyRepository extends MongoRepository<QuestClassify, String> {

}
