package com.common.mvc.article.service;


import com.common.mvc.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ArticleRepository extends MongoRepository<Article, String> {


     Optional<Article> findById(String id);

     Page findAllByStatus(int status, PageRequest pageRequest);

     Page<Article> findByTagsAndClassify(String classify, int status, PageRequest pageRequest);
}
