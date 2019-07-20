package com.article.admin.mvc.about.service;

import com.article.admin.mvc.about.entity.About;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AboutRepository extends MongoRepository<About, String> {

    About findAboutById(String id);

}
