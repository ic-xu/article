package com.common.mvc.article.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleData {
    private int curPage;
    private List<Article> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
}
