package com.article.admin.mvc.community.service.repository;


import com.article.admin.mvc.community.entity.Tags;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagsRepository extends MongoRepository<Tags, Long> {

    void deleteByTagsName(String tagsName);

}
