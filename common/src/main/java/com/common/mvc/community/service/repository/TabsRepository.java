package com.common.mvc.community.service.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.community.entity.Tabs;

public interface TabsRepository extends MongoRepository<Tabs,Long> {

    void deleteByTabName(String tabName);


}
