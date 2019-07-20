package com.article.service.mvc.feedback.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class FeedBack {

    @Id
    private String id;

    private String fromUserId;

    private String toUserId;

    private long createTime;

    private String content;

    private int status;

}
