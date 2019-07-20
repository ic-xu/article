package com.article.admin.mvc.feedback.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ReplyFeedBack {

    @Id
    private String id;

    private String replyUserId;

    private long createTime;

    private String content;
}
