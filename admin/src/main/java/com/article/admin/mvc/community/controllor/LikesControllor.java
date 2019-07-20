package com.article.admin.mvc.community.controllor;


import com.article.admin.utils.BaseResponseDto;
import com.article.admin.mvc.community.entity.Likes;
import com.article.admin.mvc.community.service.LikesServerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "收藏或喜欢")
@RequestMapping("/community/like")
public class LikesControllor {

    @Autowired
    LikesServerImp likesServerImp;


    @ApiOperation("添加喜欢按钮(会自动判断用户是添加喜欢还是取消喜欢)，所以只需要传递这一个接口即可")
    @PostMapping("/saveLikes")
    public BaseResponseDto saveLikes(Likes likes) {
        return BaseResponseDto.success(likesServerImp.save(likes));
    }

}
