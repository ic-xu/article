package com.common.mvc.comment.service;

import com.common.mvc.comment.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment comment){
      return   commentRepository.save(comment);
    }


    public void deleteById(String id){
        commentRepository.deleteById(id);
    }


    public Comment findById(String id){
       return commentRepository.findById(id).get();
    }

    public void deleteAllByArticleId(String articleId){
        commentRepository.deleteAllByArticleId(articleId);
    }


    public Page<Comment> findAllByArticleId(String id, PageRequest pageRequest){
        return commentRepository.findAllByArticleId(id,pageRequest);
    }
}
