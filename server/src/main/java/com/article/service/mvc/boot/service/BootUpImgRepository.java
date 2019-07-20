package com.article.service.mvc.boot.service;

import com.article.service.mvc.boot.entity.BootUpImg;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BootUpImgRepository extends MongoRepository<BootUpImg, String> {

    List<BootUpImg> findAllByStatus(int status);


}
