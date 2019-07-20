package com.article.service.mvc.banner.service;

import com.article.service.mvc.banner.entity.Banner;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BannerRepository extends MongoRepository<Banner,String> {

    List<Banner> findAllByStatus(int status);
}
