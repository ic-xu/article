package com.common.mvc.creative.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author chenxu
 */
@Document
@Data
public class CreativeCollect {

    @Id
    private String id;

    private long creativeId;

    private String userId;
}
