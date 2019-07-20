package com.article.service.mvc.comment.service;


import com.article.service.mvc.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,String> {

    Page<Comment> findAllByArticleId(String articleId, PageRequest pageRequest);

    int deleteAllByArticleId(String articleId);
}
