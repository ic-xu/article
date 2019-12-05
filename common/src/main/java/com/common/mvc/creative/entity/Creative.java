package com.common.mvc.creative.entity;


import com.common.mvc.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author chenxu
 * @date On 19-11-29 下午4:09
 */
@Data
public class Creative {

    @Id
    private Long id;

    private String classify;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("详细内容")
    private String content;

    private Member auther;

    private Long createTime;

    private List<String> images;

    private Long readCount;

    private Long agreeCount;

    private int status;
}
