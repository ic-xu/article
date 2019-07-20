package com.article.admin.mvc.community.service.repository;


import com.article.admin.mvc.community.entity.Tabs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TabsRepository extends MongoRepository<Tabs,Long> {

    void deleteByTabName(String tabName);


}
