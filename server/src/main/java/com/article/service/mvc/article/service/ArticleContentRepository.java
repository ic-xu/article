package com.article.service.mvc.article.service;


import com.article.service.mvc.article.entity.ArticleContent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleContentRepository extends MongoRepository<ArticleContent, String> {

     ArticleContent findByArticleId(String id);
}
