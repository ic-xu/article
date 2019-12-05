package com.common.mvc.creative.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author chenxu
 */

@Data
public class CreativeClassify {

    @Id
    private String classify;
}
