package com.article.admin.mvc.article.service;


import com.article.admin.mvc.article.entity.ArticleContent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleContentRepository extends MongoRepository<ArticleContent, String> {

     ArticleContent findByArticleId(String id);
}
