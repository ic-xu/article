//package com.tcl.community.test.controller;
//
//
//import com.tcl.community.utils.BaseResponseDto;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Api(tags = "用户相关接口")
//public class UserControllor {
//
//    @Autowired
//    UserServiceImp userService;
//
//    @ApiOperation("保存用户")
//    @PostMapping("/saveUser")
//    public BaseResponseDto saveUser(User user) {
//        return BaseResponseDto.success(userService.save(user));
//    }
//
//    @ApiOperation("查找所有")
//    @PostMapping("/fandAll")
//    public BaseResponseDto fandAll() {
//        return BaseResponseDto.success(userService.findAll());
//    }
//
//}
