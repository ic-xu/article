package com.common.mvc.community.service.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.community.entity.Tags;

public interface TagsRepository extends MongoRepository<Tags, Long> {

    void deleteByTagsName(String tagsName);

}
