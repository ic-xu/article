package com.article.service.mvc.boot.controllor;


import com.article.service.utils.BaseResponseDto;
import com.article.service.mvc.boot.entity.BootUpImg;
import com.article.service.mvc.boot.service.BootUpImgImp;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/boot")
@Api(tags = "启动图相关")
public class BootImgControllor {

    @Autowired
    BootUpImgImp bootUpImgImp;


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

}
