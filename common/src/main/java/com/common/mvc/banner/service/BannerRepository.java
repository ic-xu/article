package com.common.mvc.banner.service;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.banner.entity.Banner;

import java.util.List;

public interface BannerRepository extends MongoRepository<Banner,String> {

    List<Banner> findAllByStatus(int status);

}
