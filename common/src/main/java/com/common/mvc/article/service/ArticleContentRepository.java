package com.common.mvc.article.service;


import com.common.mvc.article.entity.ArticleContent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleContentRepository extends MongoRepository<ArticleContent, String> {

     ArticleContent findByArticleId(String id);
}
