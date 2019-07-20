package com.article.service.mvc.article.controllor;


import com.article.service.mvc.comment.service.ReplyCommentService;
import com.article.service.mvc.community.service.LikesServerImp;
import com.article.service.utils.BaseResponseDto;
import com.article.service.utils.IdWorker;
import com.article.service.mvc.article.entity.Article;
import com.article.service.mvc.article.entity.ArticleContent;
import com.article.service.mvc.article.service.ArticleServerImp;
import com.article.service.mvc.comment.service.CommentService;
import com.article.service.mvc.user.entity.Member;
import com.article.service.mvc.user.service.UserServiceImp;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "文章相关接口")
@RequestMapping("/article")
public class ArticleControllor {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    private ArticleServerImp articleServerImp;

    @Autowired
    private LikesServerImp likesServerImp;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyCommentService replyCommentService;

    /**
     * @param articleTitle
     * @param articleDescribe
     * @param userId
     * @param keyWord
     * @param tags
     * @param image
     * @param content
     * @return
     */
    @ApiOperation("发表文章接口")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping("/saveArticle")
    public BaseResponseDto saveArticle(@RequestParam(required = true, defaultValue = "") String articleTitle,
                                       @RequestParam(required = true, defaultValue = "") String articleDescribe,
                                       @RequestParam(required = true, defaultValue = "") String userId,
                                       @RequestParam(required = false, defaultValue = "") String keyWord, String tags,
                                       @RequestParam(required = false, defaultValue = "") String image,
                                       @RequestParam(required = true, defaultValue = "") String content) {

        Article article = new Article();
        article.setArticleId("A" + new IdWorker().nextId() + "" + System.currentTimeMillis());
        article.setArticleTitle(articleTitle);
        article.setArticleDescribe(articleDescribe);
        article.setHappenTime(System.currentTimeMillis());
        Member member = new Member();
        member.setUserId(userId);
        article.setMember(member);
        article.setKeyWord(keyWord);
        article.setTags(tags);
        article.setLike(false);
        article.setStatus(0);
        article.setNewsImg(image);
        System.out.println(article);
        article.setStatus(1);
        if (articleServerImp.insertArticle(article)) {
            ArticleContent articleContent = new ArticleContent();
            articleContent.setArticleId(article.getArticleId());
            articleContent.setContent(content);
            articleServerImp.saveContent(articleContent);
            return BaseResponseDto.success(true);
        }
        return BaseResponseDto.success(false);
    }



    /**
     * 根据标签获取文章列表接口
     *
     * @param page
     * @param pageSize
     * @param tags
     * @return
     */
    @ApiOperation("根据tars获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "所请求的是第几页，从0开始，默认是0页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "请求的每一页的大小，默认是20", dataType = "int"),
            @ApiImplicitParam(name = "tags", value = "标签", dataType = "string", defaultValue = "")
    })
    @GetMapping("/getAllArticle")
    public BaseResponseDto getAllArticle(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize,
                                         @RequestParam(defaultValue = "", required = false) String tags) {
        List<Article> articleForPage = articleServerImp.getArticleForPage(page, pageSize, tags);
        System.out.println("服务器返回数据============>>>>>>>>>>> " + articleForPage);
        return BaseResponseDto.success(articleForPage);
    }


    @ApiOperation("获取单个文章接口")
    @GetMapping("/getArticleById")
    public BaseResponseDto getArticleById(String id) {
        Article oneArticle = articleServerImp.getOneArticle(id);
        if (null != oneArticle.getMember()) {
            oneArticle.setMember(userServiceImp.getMemberById(oneArticle.getMember().getUserId()));
        }
        ArticleContent byArticleId = articleServerImp.findContentByArticleId(id);
        oneArticle.setContent(byArticleId.getContent());
        return BaseResponseDto.success(oneArticle);
    }



    @ApiOperation("删除文章接口")
    @PostMapping("/deleteArticle")
    public BaseResponseDto deleteArticle(String article,String userId) {

        Article byArticleId = articleServerImp.findByArticleId(article);
        if(null!=article&&null!=byArticleId.getMember()){
                if(byArticleId.getMember().getUserId().equals(userId)){
                    articleServerImp.delete(article);
                    //分别删除评论信息和回复信息
                    commentService.deleteAllByArticleId(article);
                    replyCommentService.deleteAllByArticleId(article);
                    return BaseResponseDto.success();
                }else
                    return BaseResponseDto.error(400,"该用户没有权限删除该文章");
        }
        return BaseResponseDto.error(400,"没有相关文章");
    }


}
