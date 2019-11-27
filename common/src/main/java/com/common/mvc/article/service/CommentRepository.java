package com.common.mvc.article.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.article.entity.Comment;

public interface CommentRepository extends MongoRepository<Comment,String> {

    Page<Comment> findAllByArticleId(String articleId, PageRequest pageRequest);

    int deleteAllByArticleId(String articleId);
}
