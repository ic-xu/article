package com.article.service.mvc.community;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.common.mvc.community.entity.Tags;
import com.common.mvc.community.service.TagsServiceImp;
import com.common.utils.BaseResponseDto;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "社区标签相关")
@RequestMapping("/community/tags")
public class TagsControllor {

    @Autowired
    TagsServiceImp tagsServiceImp;


    @ApiOperation("添加标签")
    @ApiIgnore
    @PostMapping("/saveTags")
    public BaseResponseDto saveTags(Tags tags) {
        if (null == tagsServiceImp.saveTypeOne(tags))
            return BaseResponseDto.error(400, "标签已经存在");
        else return BaseResponseDto.success(tagsServiceImp.saveTypeOne(tags));
    }


    @ApiOperation("获取所有标签")
    @GetMapping("/getAllTags")
    public BaseResponseDto getAllTags(){
        return BaseResponseDto.success(tagsServiceImp.getAllTargs());
    }


}
