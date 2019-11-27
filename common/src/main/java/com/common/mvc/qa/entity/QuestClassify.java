package com.common.mvc.qa.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ubuntu
 * @date  19-11-26 下午8:22
 */

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestClassify {

    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("类名")
    private String classifyName;
}
