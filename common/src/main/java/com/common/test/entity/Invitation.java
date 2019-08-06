package com.common.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invitation {
    @Id
    private Long postId;

    private String postName;

    private String postImgSmall;

    private String postDescribe;

    private String originator;

    private String content;

    private String happenTime;

    private Long categrotyId;

    private long comments;

}