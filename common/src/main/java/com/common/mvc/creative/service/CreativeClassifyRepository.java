package com.common.mvc.creative.service;


import com.common.mvc.creative.entity.CreativeClassify;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreativeClassifyRepository extends MongoRepository<CreativeClassify, Long> {


    CreativeClassify findByClassify(String classify);
}
