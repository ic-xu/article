package com.article.service.mvc.community.controllor;


import com.article.service.mvc.community.entity.Tabs;
import com.article.service.mvc.community.service.TabsServiceImp;
import com.article.service.utils.BaseResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "社区标题栏相关")
@RequestMapping("/community/tabs")
public class TabsControllor {

    @Autowired
    TabsServiceImp tabsServiceImp;


    @ApiOperation("添加标题栏")
    @ApiIgnore
    @PostMapping("/saveTabs")
    public BaseResponseDto saveTabs(Tabs tabs) {
        Tabs tabs1 = tabsServiceImp.saveTabs(tabs);
        if (null == tabs1)
            return BaseResponseDto.error(400, "标题栏已经存在");
        else return BaseResponseDto.success(tabs1);
    }


    @ApiOperation("修改标题栏")
    @ApiIgnore
    @PostMapping("/updateTypeTow")
    public BaseResponseDto updateTypeTow(Tabs tabs) {
        return BaseResponseDto.success(tabsServiceImp.saveTabs(tabs));
    }


    @ApiOperation("获取所有标题")
    @GetMapping("/getAll")
    public BaseResponseDto getAll() {
        return BaseResponseDto.success(tabsServiceImp.getAll());
    }


}
