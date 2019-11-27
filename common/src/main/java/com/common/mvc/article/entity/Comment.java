package com.common.mvc.article.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import com.common.mvc.member.entity.Member;

@Data
public class Comment {

    @Id
    private String commentId;

    private String commentContent;

    private long createTime;

    private String articleId;

    private Member member;

}
