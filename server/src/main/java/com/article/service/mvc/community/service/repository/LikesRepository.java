package com.article.service.mvc.community.service.repository;

import com.article.service.mvc.community.entity.Likes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikesRepository extends MongoRepository<Likes, String> {

     Likes findOneByUserIdAndArticleId(String userId,String articleId);

     void deleteById(String id);

     long countByArticleId(String article);
}
