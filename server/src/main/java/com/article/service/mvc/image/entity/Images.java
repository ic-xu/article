package com.article.service.mvc.image.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Images {
        private String src;
        private String thumb;
        private String alt;
        private String pid;
}
