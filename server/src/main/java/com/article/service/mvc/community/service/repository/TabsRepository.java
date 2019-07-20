package com.article.service.mvc.community.service.repository;

import com.article.service.mvc.community.entity.Tabs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TabsRepository extends MongoRepository<Tabs,Long> {


}
