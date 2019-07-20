package com.article.service.mvc.community.service.repository;

import com.article.service.mvc.community.entity.Tags;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TagsRepository extends MongoRepository<Tags, Long> {

}
