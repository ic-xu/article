package com.common.mvc.banner.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Banner {

    @Id
    private long id;

    private String url;

    private String imagePath;

    private String title;

    private int status;

}
