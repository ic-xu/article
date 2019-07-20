package com.article.service.mvc.comment.service;


import com.article.service.mvc.comment.entity.ReplyComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReplyCommentRepository extends MongoRepository<ReplyComment,String> {

    Page<ReplyComment> findAllByArticleIdAndToMemberId(String articleId, String toMemberId, PageRequest pageRequest);

    int deleteAllByArticleId(String article);

    int deleteAllByToMemberId(String toMemberId);

}
