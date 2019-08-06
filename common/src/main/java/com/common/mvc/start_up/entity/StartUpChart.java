package com.common.mvc.start_up.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class StartUpChart {

    @Id
    private String imgUrl;

    private String linkUrl;
}
