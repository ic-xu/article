package com.article.service.mvc.start_up;

import com.common.mvc.start_up.service.StartUpChartServiceImp;
import com.common.utils.BaseResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "启动图相关")
@RequestMapping("/startup")
public class StartUpControllor {

    @Autowired
    StartUpChartServiceImp startUpChartServiceImp;



//    @PostMapping("/save")
//    @ApiOperation("保存启动图")
//    public BaseResponseDto save(String linkUrl,@RequestParam(name ="file",required = false) MultipartFile file) throws IOException {
//
//        String upload = UploadUtils.fileUpload01(file);
//        if(null!=upload)
//        return BaseResponseDto.success(new StartUpChart(upload,linkUrl));
//        else return BaseResponseDto.error(500,"服务器内部出错");
//    }

    @PostMapping("/get/json")
    @ApiOperation("删除启动图")
    public BaseResponseDto findAll(){

        return BaseResponseDto.success(startUpChartServiceImp.findAll());
    }



}
