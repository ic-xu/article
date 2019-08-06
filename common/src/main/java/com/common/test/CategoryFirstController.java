package com.common.test;//package com.tcl.community.test;
//
//
//import com.tcl.community.test.service.CategoryFirstService;
//import com.tcl.community.utils.BaseResponseDto;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import org.jboss.logging.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Api("演示案例")
//@RestController
//public class CategoryFirstController {
//
//    Logger logger = Logger.getLogger(getClass());
//
//    @Autowired
//    CategoryFirstService categoryFirstService;
//
//
//    @ApiOperation(value="/get/hello方法")
//    @ApiImplicitParam(name = "name",value = "用户信息参数",required = true)
//    @GetMapping("/getIndex")
//    public String getIndex(String name) {
//        logger.info("=====getIndex======");
//        return "getIndex-hello-com.tcl.community.test-01"+name;
//    }
//
//    @ApiOperation(value="获取一级分分类方法")
//    @GetMapping("/getAllCategory")
//    public BaseResponseDto getAllCategory(){
//        return BaseResponseDto.success(categoryFirstService.getAllCategory("ss"));
//    }
//}
