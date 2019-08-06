package com.common.mvc.community.service.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.community.entity.Likes;

public interface LikesRepository extends MongoRepository<Likes, String> {

     Likes findOneByUserIdAndArticleId(String userId, String articleId);

     void deleteById(String id);

}
