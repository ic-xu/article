package com.article.service.mvc.article.controllor;


import com.article.service.mvc.comment.service.ReplyCommentService;
import com.article.service.utils.BaseGetData;
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
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "文章相关接口")
@RequestMapping("/article")
public class ArticleControllor {

    private UserServiceImp userServiceImp;

    private ArticleServerImp articleServerImp;

    private CommentService commentService;

    private ReplyCommentService replyCommentService;

    @Autowired
    public void setArticleServerImp(ArticleServerImp articleServerImp) {
        this.articleServerImp = articleServerImp;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setReplyCommentService(ReplyCommentService replyCommentService) {
        this.replyCommentService = replyCommentService;
    }

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    /**
     * @param articleTitle    文章标题
     * @param articleDescribe 描述
     * @param userId          用户id
     * @param keyWord         关键字
     * @param tags            标签
     * @param image           图像
     * @param content         内容
     * @return 返回值
     */
    @ApiOperation("发表文章接口")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping("/saveArticle")
    public BaseResponseDto saveArticle(@RequestParam(defaultValue = "") String articleTitle,
                                       @RequestParam(defaultValue = "") String articleDescribe,
                                       @RequestParam(defaultValue = "") String userId,
                                       @RequestParam(required = false, defaultValue = "") String keyWord, String tags,
                                       @RequestParam(required = false, defaultValue = "") String image,
                                       @RequestParam(defaultValue = "") String content) {

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
     * @param page     获取第几页数据
     * @param pageSize 每一页大小
     * @return 返回值
     */
    @ApiOperation("根据tars获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "所请求的是第几页，从0开始，默认是0页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "请求的每一页的大小，默认是20", dataType = "int"),
            @ApiImplicitParam(name = "classify", value = "分类", dataType = "string", defaultValue = " ")
    })
    @GetMapping("/getAllArticle")
    public BaseResponseDto getAllArticle(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize,
                                         @RequestParam(defaultValue = "", required = false) String classify) {
        Page<Article> articleForPage = articleServerImp.getArticleForPage(page, pageSize, classify, 1);

        System.out.println("服务器返回数据============>>>>>>>>>>> " + articleForPage);
        return BaseResponseDto.success(new BaseGetData<>(page, articleForPage.getContent(), articleForPage.getTotalPages()));
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
    public BaseResponseDto deleteArticle(String article, String userId) {

        Article byArticleId = articleServerImp.findByArticleId(article);
        if (null != article && null != byArticleId.getMember()) {
            if (byArticleId.getMember().getUserId().equals(userId)) {
                articleServerImp.delete(article);
                //分别删除评论信息和回复信息
                commentService.deleteAllByArticleId(article);
                replyCommentService.deleteAllByArticleId(article);
                return BaseResponseDto.success();
            } else
                return BaseResponseDto.error(400, "该用户没有权限删除该文章");
        }
        return BaseResponseDto.error(400, "没有相关文章");
    }


}
