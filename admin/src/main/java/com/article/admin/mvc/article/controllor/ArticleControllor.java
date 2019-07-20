package com.article.admin.mvc.article.controllor;


import com.article.admin.mvc.comment.service.CommentService;
import com.article.admin.mvc.user.entity.Member;
import com.article.admin.mvc.user.service.UserServiceImp;
import com.article.admin.utils.BaseResponseDto;
import com.article.admin.mvc.article.entity.Article;
import com.article.admin.mvc.article.entity.ArticleContent;
import com.article.admin.mvc.article.service.ArticleServerImp;
import com.article.admin.mvc.comment.service.ReplyCommentService;
import com.article.admin.mvc.community.service.LikesServerImp;
import com.article.admin.utils.IdWorker;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private ReplyCommentService replyCommentService;

    @Autowired
    private CommentService commentService;


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
        article.setStatus(1);
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


    @ApiOperation("根据tars获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "所请求的是第几页，从0开始，默认是0页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "请求的每一页的大小，默认是20", dataType = "int"),
            @ApiImplicitParam(name = "tags", value = "标签", dataType = "string", defaultValue = ""),
            @ApiImplicitParam(name = "status", value = "审核状态", dataType = "int", defaultValue = "-1")
    })
    @GetMapping("/getAllArticle")
    public BaseResponseDto getAllArticle(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize,
                                         @RequestParam(defaultValue = "", required = false) String tags,
                                         @RequestParam(name = "status", defaultValue = "-1") int status
    ) {
        List<Article> articleForPage = articleServerImp.getArticleForPage(status, page, pageSize, tags);
        System.out.println("服务器返回数据============>>>>>>>>>>> " + articleForPage);
        return BaseResponseDto.success(articleForPage);
    }


    @ApiOperation("获取文章详细内容")
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


    /**
     * 根据状态查找文章
     *
     * @param status
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @ApiOperation("根据状态查找文章")
    @GetMapping("/findAllByStatus")
    public BaseResponseDto findAllByStatus(@RequestParam(defaultValue = "-1") int status,
                                           @RequestParam(defaultValue = "20") int pageSize,
                                           @RequestParam(defaultValue = "0") int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, new Sort(Sort.Order.desc("happenTime")));
        return BaseResponseDto.success(articleServerImp.findAllByStatus(status, pageRequest));
    }


    /**
     * 审核文章
     * @param articleId 文章Id
     * @return
     */
    @ApiOperation("审核文章")
    @PostMapping("/statusChange")
    public BaseResponseDto statusChange(@RequestParam(required = true) String articleId) {

        Article oneArticle = articleServerImp.getOneArticle(articleId);
        oneArticle.setStatus(1);
        oneArticle.setStatusChangeTime(System.currentTimeMillis());
        return BaseResponseDto.success(articleServerImp.save(oneArticle));
    }


    @ApiOperation("删除文章接口")
    @GetMapping("/deleteArticle")
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
