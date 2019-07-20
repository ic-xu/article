package com.article.admin.mvc.banner.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Banner {

    @Id
    private String id;

    private String imgUrl;

    private String link;

    private int status;

}
