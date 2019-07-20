package com.article.admin.mvc.comment.entity;

import com.article.admin.mvc.user.entity.Member;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ReplyComment {

    @Id
    private String commentId;

    private String commentContent;

    private long createTime;

    private String articleId;

    private Member member;

    private String toMemberId;
}
