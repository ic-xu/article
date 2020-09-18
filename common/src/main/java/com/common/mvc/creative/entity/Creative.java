package com.common.mvc.creative.entity;


import com.common.mvc.user.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ApiModelProperty("描述信息")
    private String describe;

    private Member auther;

    private Long createTime;

    private List<String> images;

    private Long readCount;

    private Long collectCount;

    private boolean hot = true;

    private boolean fresh = true;

    @JsonIgnore
    private int status;

    @ApiModelProperty("默认这个为false，只有当用户点击之后才会编程true")
    private boolean collect = false;
}
