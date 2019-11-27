package com.common.mvc.article.service;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.article.entity.Likes;

public interface LikesRepository extends MongoRepository<Likes, String> {

     Likes findOneByUserIdAndArticleId(String userId, String articleId);

     void deleteById(String id);

}
