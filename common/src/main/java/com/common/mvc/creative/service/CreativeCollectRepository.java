package com.common.mvc.creative.service;


import com.common.mvc.creative.entity.CreativeCollect;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author chenxu
 */
public interface CreativeCollectRepository extends MongoRepository<CreativeCollect, String> {

}
