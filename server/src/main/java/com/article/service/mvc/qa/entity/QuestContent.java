package com.article.service.mvc.qa.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * Created by ubuntu
 * On 19-7-20 下午12:46
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestContent {

    @Id
    @ApiModelProperty("id")
    private String questContentId;

    @ApiModelProperty("问题详细内容")
    private String content;
}
