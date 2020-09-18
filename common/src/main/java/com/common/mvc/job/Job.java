package com.common.mvc.job;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Job {

    private String jobName;

    @ApiModelProperty("所属分类")
    private String classiFication;

}
