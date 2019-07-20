package com.article.service.mvc.member.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Member {
    @Id
    private String userId;
    private String phone;
    private String job;
    private String password;
    private String img;
    private String desc;
    private long createTime;
}
