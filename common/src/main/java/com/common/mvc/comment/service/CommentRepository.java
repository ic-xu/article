package com.common.mvc.comment.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.comment.entity.Comment;

public interface CommentRepository extends MongoRepository<Comment,String> {

    Page<Comment> findAllByArticleId(String articleId, PageRequest pageRequest);

    int deleteAllByArticleId(String articleId);
}
