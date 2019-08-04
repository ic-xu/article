package com.article.service.mvc.banner.controllor;


import com.article.service.config.ServerConfig;
import com.article.service.mvc.banner.entity.Banner;
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

import java.io.File;
import java.util.List;

@Api(tags = "轮播图相关")
@RestController()
@RequestMapping("/banner")
public class BannerControllor {

    private BannerService bannerService;

    private ServerConfig serverConfig;
    @Autowired
    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }
    @Autowired
    public void setBannerService(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    /**
     * 前端获取轮播图,状态为1是可显示的
     *
     * @return
     */
    @GetMapping("/json")
    @ApiOperation("获取轮播图")
    @ApiResponses({
            @ApiResponse(code = 400, message = "操作失败"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    public BaseResponseDto getBannerList() {
        List<Banner> all = bannerService.findAll(1);
        for (Banner banner : all) {
            banner.setImagePath(serverConfig.getUrl() + File.separator + banner.getImagePath());
        }
        return BaseResponseDto.success(all);
    }
}
