package com.common.mvc.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import com.common.mvc.comment.entity.ReplyComment;

@Repository
public class ReplyCommentService {

    @Autowired
    private ReplyCommentRepository replyCommentRepository;


    public ReplyComment save(ReplyComment comment){
        return   replyCommentRepository.save(comment);
    }


    public void deleteById(String id){
        replyCommentRepository.deleteById(id);
    }

    public void deleteAllByArticleId(String article){
        replyCommentRepository.deleteAllByArticleId(article);
    }

    public void deleteAllByToMemberId(String toMemberId){
        replyCommentRepository.deleteAllByToMemberId(toMemberId);
    }

    public Page<ReplyComment> findAllByArticleIdAndToMemberId(String id, String toMemberId , PageRequest pageRequest){
        return replyCommentRepository.findAllByArticleIdAndToMemberId(id,toMemberId,pageRequest);
    }

}
