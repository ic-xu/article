package com.article.admin.mvc.boot.controllor;


import com.article.admin.mvc.boot.entity.BootUpImg;
import com.article.admin.mvc.boot.service.BootUpImgImp;
import com.article.admin.utils.BaseResponseDto;
import com.article.admin.utils.UploadUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
@RequestMapping("/boot")
@Api(tags = "启动图相关")
public class BootImgControllor {

    @Autowired
    BootUpImgImp bootUpImgImp;


    @PostMapping("/insert")
    @ApiOperation("(后台管理)新增配置启动图接口")
    @ApiResponses({
            @ApiResponse(code = 400, message = "上传失败"),
            @ApiResponse(code = 0, message = "上传成功")
    })

    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "是否展示,0不展示，1展示,默认0", defaultValue = "0", required = true, paramType = "int"),
            @ApiImplicitParam(name = "file", value = "上传文件", required = true)
    })
    @Transactional
    public BaseResponseDto insert(@RequestParam(value = "status", defaultValue = "0") int status, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        if (null == file || file.isEmpty())
            return BaseResponseDto.error(400, "图片不存在");
        if (!file.getContentType().contains("image"))
            return BaseResponseDto.error(400, "请上传图片格式文件");
        String fileName = UploadUtils.fileUpload01(file);

        BootUpImg bootUpImg = new BootUpImg();

        bootUpImg.setId(fileName);

        bootUpImg.setStatus(status);
        bootUpImg.setUrl(fileName);
        BootUpImg insert = bootUpImgImp.insert(bootUpImg);
        return BaseResponseDto.success(insert);
    }


    @GetMapping("/getImag")
    @ApiOperation("获取启动图")
    @ApiResponses({
            @ApiResponse(code = 400, message = "操作失败"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    public BaseResponseDto bootImg_getOne() {
        BootUpImg oneImg = bootUpImgImp.getOneImg(1);
        if (null != oneImg)
            return BaseResponseDto.success(oneImg);
        else return BaseResponseDto.error(400, "暂时没有相关数据");
    }


    @GetMapping("/getAll")
    @ApiOperation("获取所有启动图列表")
    @ApiResponses({
            @ApiResponse(code = 400, message = "操作失败"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    public BaseResponseDto getAll() {
        return BaseResponseDto.success(bootUpImgImp.getbootImg(-1));
    }


    @PostMapping("/delete")
    @ApiOperation("删除启动图")
    public BaseResponseDto delete(String id) {
        bootUpImgImp.delete(id);
        return BaseResponseDto.success();
    }

}
