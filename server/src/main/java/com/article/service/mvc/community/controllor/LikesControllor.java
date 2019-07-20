package com.article.service.mvc.community.controllor;


import com.article.service.mvc.community.entity.Likes;
import com.article.service.mvc.community.service.LikesServerImp;
import com.article.service.utils.BaseResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "收藏或喜欢")
@RequestMapping("/community/like")
public class LikesControllor {

    @Autowired
    LikesServerImp likesServerImp;


    @ApiOperation("添加喜欢按钮(会自动判断用户是添加喜欢还是取消喜欢)，所以只需要传递这一个接口即可")
    @PostMapping("/saveLikes")
    public BaseResponseDto saveLikes(Likes likes) {
        likesServerImp.save(likes);
        return BaseResponseDto.success(likesServerImp.countByArticleId(likes.getArticleId()));
    }


    @ApiOperation("获取喜欢按钮（已不用）")
    @ApiIgnore
    @PostMapping("/countByArticleId")
    public BaseResponseDto countByArticleId(String article) {
        return BaseResponseDto.success(likesServerImp.countByArticleId(article));
    }


}
