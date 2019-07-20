package com.article.admin.mvc.banner.controllor;


import com.article.admin.mvc.banner.entity.Banner;
import com.article.admin.mvc.banner.service.BannerService;
import com.article.admin.utils.BaseResponseDto;
import com.article.admin.utils.UploadUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    @ApiOperation("获取banner列表")
    @ApiResponses({
            @ApiResponse(code = 400, message = "操作失败"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    @ApiImplicitParam(name = "status",value = "状态，-1表示所有轮播图，0表示不显示的轮播图，1表示显示的轮播图")
    public BaseResponseDto getBannerList(@RequestParam(name = "status",defaultValue = "0") int status) {
        return BaseResponseDto.success(bannerService.findAll(status));
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "link",value = "点击图片需要跳转的url",required = true),
            @ApiImplicitParam(name = "status",value = "是否显示，1表示需要显示，0表示不需要显示",required = true),
            @ApiImplicitParam(name = "file",value = "图片文件，只接受图片格式文件",required = true)
    })
    public BaseResponseDto insertBanner(String link, int status, @RequestParam(required = false, value = "file") MultipartFile file) throws IOException {

        if (null == file)
            return BaseResponseDto.error(400, "图片为空");
        if (!file.getContentType().contains("image"))
            return BaseResponseDto.error(400, "只接受图片类型的文件");
        String s = UploadUtils.fileUpload01(file);

        Banner banner = new Banner();
        banner.setId(s);
        banner.setImgUrl(s);
        banner.setLink(link);
        banner.setStatus(status);


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
