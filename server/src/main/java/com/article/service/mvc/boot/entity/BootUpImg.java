package com.article.service.mvc.boot.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BootUpImg {

    @Id
    private String id;

    private String url;

    private int status;
}
