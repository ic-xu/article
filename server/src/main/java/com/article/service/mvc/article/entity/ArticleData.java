package com.article.service.mvc.article.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleData {
    private int code;
    private String msg;
    private long count;
    private List<Article> data;
}
