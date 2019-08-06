package com.common.mvc.community.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@ApiModel
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Tags {

    @Id
    @ApiModelProperty(value = "标签名称")
    private String tagsName;
    @ApiModelProperty(value = "类备用")
    private String apare;
    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "标题名称")
    private String tabsName;

//    @ApiModelProperty(value = "二级分类链接数组")
//    private List<TowClassification> towClassificationList;

}
