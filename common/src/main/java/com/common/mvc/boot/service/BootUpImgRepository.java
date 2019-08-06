package com.common.mvc.boot.service;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.boot.entity.BootUpImg;

import java.util.List;

public interface BootUpImgRepository extends MongoRepository<BootUpImg, String> {

    List<BootUpImg> findAllByStatus(int status);


}
