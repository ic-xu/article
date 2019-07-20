package com.article.service.mvc.banner.controllor;


import com.article.service.utils.BaseResponseDto;
import com.article.service.mvc.banner.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "轮播图相关")
@RestController()
@RequestMapping("/banner")
public class BannerControllor {

    @Autowired
    private BannerService bannerService;

    /**
     * 前端获取轮播图,状态为1是可显示的
     *
     * @return
     */
    @GetMapping("/getBannerList")
    @ApiOperation("获取轮播图")
    @ApiResponses({
            @ApiResponse(code = 400, message = "操作失败"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    public BaseResponseDto getBannerList() {
        return BaseResponseDto.success(bannerService.findAll(1));
    }
}
