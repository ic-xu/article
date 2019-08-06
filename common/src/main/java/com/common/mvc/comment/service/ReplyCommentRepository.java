package com.common.mvc.comment.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.comment.entity.ReplyComment;

public interface ReplyCommentRepository extends MongoRepository<ReplyComment,String> {

    Page<ReplyComment> findAllByArticleIdAndToMemberId(String articleId, String toMemberId, PageRequest pageRequest);

    int deleteAllByArticleId(String article);

    int deleteAllByToMemberId(String toMemberId);

}
