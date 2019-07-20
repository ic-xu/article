package com.article.service.mvc.article.service;

import com.article.service.mvc.article.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {


     Article findByArticleId(String id);

}
