package com.article.admin.controllor.about;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.common.mvc.about.entity.About;
import com.common.mvc.about.service.AboutService;
import com.common.utils.BaseResponseDto;

@RestController
@RequestMapping("/about")
@Api(tags = "关于我们")
public class AboutControllor {


   private AboutService aboutService;

    @Autowired
    public void setAboutService(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @PostMapping("/about")
    @ApiOperation("编辑关于我们界面")
    @ApiImplicitParam(name = "content",value ="编辑好的富文本页面",required = true)
    public BaseResponseDto save(String content) {
        About about = new About();
        about.setContent(content);
        return BaseResponseDto.success(aboutService.save(about));
    }

    @PostMapping("/getAbout")
    @ApiOperation("获取关于我们界面，返回的是一个html页面")
    public BaseResponseDto getAbout() {
        About about = aboutService.find();
        return BaseResponseDto.success(about.getContent());
    }

}
