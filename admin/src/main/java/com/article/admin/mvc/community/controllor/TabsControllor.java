package com.article.admin.mvc.community.controllor;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.common.mvc.community.entity.Tabs;
import com.common.mvc.community.service.TabsServiceImp;
import com.common.utils.BaseResponseDto;

@RestController
@Api(tags = "社区标题栏相关")
@RequestMapping("/community/tabs")
public class TabsControllor {

    @Autowired
    TabsServiceImp tabsServiceImp;


    @ApiOperation("添加标题栏")
    @PostMapping("/saveTabs")
    public BaseResponseDto saveTabs(Tabs tabs) {
        Tabs tabs1 = tabsServiceImp.insertTabs(tabs);
        if (null == tabs1)
            return BaseResponseDto.error(400, "标题栏已经存在");
        else return BaseResponseDto.success(tabs1);
    }


    @ApiOperation("修改标题栏")
    @PostMapping("/updateTypeTow")
    public BaseResponseDto updateTypeTow(Tabs tabs) {
        return BaseResponseDto.success(tabsServiceImp.saveTabs(tabs));
    }


    @ApiOperation("修改标题栏")
    @GetMapping("/getAll")
    public BaseResponseDto getAll() {
        return BaseResponseDto.success(tabsServiceImp.getAll());
    }


    @ApiOperation("删除标签")
    @PostMapping("/delete")
    public BaseResponseDto delete(String tabName) {
        tabsServiceImp.delete(tabName);
        return BaseResponseDto.success("删除成功");
    }


}
