package com.article.service.mvc.comment.entity;

import com.article.service.mvc.user.entity.Member;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Comment {

    @Id
    private String commentId;

    private String commentContent;

    private long createTime;

    private String articleId;

    private Member member;

}
