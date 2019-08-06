package com.common.mvc.about.service;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.about.entity.About;

public interface AboutRepository extends MongoRepository<About, String> {

    About findAboutById(String id);

}
