package com.article.admin.mvc.banner;


import com.article.admin.config.ServerConfig;
import com.article.admin.utils.UploadUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.common.mvc.banner.entity.Banner;
import com.common.mvc.banner.service.BannerService;
import com.common.utils.BaseResponseDto;
import com.common.utils.IdWorker;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Api(tags = "轮播图相关")
@RestController()
@RequestMapping("/banner")
public class BannerControllor {

    private BannerService bannerService;

    private ServerConfig serverConfig;
    @Autowired
    public void setBannerService(BannerService bannerService) {
        this.bannerService = bannerService;
    }
    @Autowired
    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    /**
     * 前端获取轮播图,状态为1是可显示的
     *
     * @return
     */
    @GetMapping("/getBannerList")
    @ApiOperation("获取banner列表")
    @ApiResponses({
            @ApiResponse(code = 400, message = "操作失败"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    @ApiImplicitParam(name = "status",value = "状态，-1表示所有轮播图，0表示不显示的轮播图，1表示显示的轮播图")
    public BaseResponseDto getBannerList(@RequestParam(name = "status",defaultValue = "0") int status) {
        List<Banner> all = bannerService.findAll(status);
        for (Banner banner : all) {
            banner.setImagePath(serverConfig.getUrl() + File.separator + banner.getImagePath());
        }
        return BaseResponseDto.success(all);
    }


    /**
     * 前端获取轮播图,状态为1是可显示的
     *
     * @return
     */
    @PostMapping("/insertBanner")
    @ApiOperation("新增banner轮播图")
    @ApiResponses({
            @ApiResponse(code = 400, message = "操作失败"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    public BaseResponseDto insertBanner(Banner banner, @RequestParam(value = "file") MultipartFile file) throws IOException {

        if (null == file)
            return BaseResponseDto.error(400, "图片为空");
        if (!file.getContentType().contains("image"))
            return BaseResponseDto.error(400, "只接受图片类型的文件");
        String name = UploadUtils.fileUpload01(file);

        banner.setId(new IdWorker().nextId());
        banner.setImagePath(name);
        Banner insert = bannerService.insert(banner);
        if (null != insert)
            return BaseResponseDto.success(insert);
        else return BaseResponseDto.error(400, "该轮播图已存在");
    }


    /**
     * 前端获取轮播图,状态为1是可显示的
     *
     * @return
     */
    @GetMapping("/deleteBanner")
    @ApiOperation("删除轮播图")
    @ApiResponses({
            @ApiResponse(code = 400, message = "操作失败"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    public BaseResponseDto deleteBanner(String id) {
        bannerService.delete(id);

        return BaseResponseDto.success("删除成功");
    }


    /**
     * 前端获取轮播图,状态为1是可显示的
     *
     * @return
     */
    @PostMapping("/updateBanner")
    @ApiOperation("修改banner轮播图，需要传所有的属性一起")
    @ApiResponses({
            @ApiResponse(code = 400, message = "操作失败"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    public BaseResponseDto updateBanner(Banner banner) {
        Banner insert = bannerService.save(banner);
        if (null != insert)
            return BaseResponseDto.success(insert);
        else return BaseResponseDto.error(400, "该轮播图已存在");
    }

}
