package com.article.service.mvc.image.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ImageData {

    private String title;
    private String id;
    private long start;
    private List<Images> data;
}


