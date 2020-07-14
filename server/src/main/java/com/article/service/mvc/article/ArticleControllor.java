package com.article.service.mvc.article;


import com.common.utils.HtmlUtils;
import com.common.utils.TimeUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.common.mvc.article.entity.Article;
import com.common.mvc.article.entity.ArticleContent;
import com.common.mvc.article.entity.ArticleData;
import com.common.mvc.article.service.ArticleServerImp;
import com.common.mvc.article.service.CommentService;
import com.common.mvc.article.service.ReplyCommentService;
import com.common.mvc.user.service.MemberService;
import com.common.utils.BaseResponseDto;
import com.common.utils.IdWorker;

@RestController
@Api(tags = "文章相关接口")
@RequestMapping("/article")
public class ArticleControllor {

    private MemberService memberService;

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
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * article
     *
     * @return 返回值
     */
    @ApiOperation("发表文章接口")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping("/saveArticle")
    public BaseResponseDto saveArticle(@RequestBody Article article) {

        article.setId("A" + IdWorker.getInstance().nextId());
        article.setHappenTime(System.currentTimeMillis());

        article.setHappenTime(System.currentTimeMillis());
        article.setLike(false);
        article.setStatus(0);
        System.out.println(article);
        article.setStatus(1);
        String contentHtml = HtmlUtils.addTitleAndTime(article.getContent(), article.getArticleTitle(), TimeUtils.timeStamp2dateString(article.getHappenTime()));
        article.setContent(contentHtml);
        if (articleServerImp.insertArticle(article)) {
//            ArticleContent articleContent = new ArticleContent();
//            articleContent.setArticleId(article.getId());
//            //拼接标题和时间等
////            String contentHtml = HtmlUtils.addTitleAndTime(article.getContent(), article.getArticleTitle(), TimeUtils.timeStamp2dateString(article.getHappenTime()));
//            articleContent.setContent(contentHtml);
//            articleServerImp.saveContent(articleContent);
//            article.setContent(null);
//            articleServerImp.save(article);
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
    @GetMapping("/getArticle")
    public BaseResponseDto getAllArticle(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize,
                                         @RequestParam(defaultValue = "", required = false) String classify) {
        Page<Article> articleForPage = articleServerImp.getArticleForPage(page, pageSize, classify, 1);

        System.out.println("服务器返回数据============>>>>>>>>>>> " + articleForPage);
//        for (int i = 0; i < articleForPage.getContent().size(); i++) {
//            articleForPage.getContent().get(i).setContent(serverConfig.getUrl() + "/article/" + articleForPage.getContent().get(i).getId() + "/html");
//            articleForPage.getContent().get(i).setId(null);
//        }
        ArticleData articleData = new ArticleData();
        articleData.setCurPage(page);
        articleData.setDatas(articleForPage.getContent());
        articleData.setPageCount(articleForPage.getTotalPages());
        articleData.setSize(articleForPage.getSize());

//        return BaseResponseDto.success(new BaseGetData<>(page, articleForPage.getContent(), articleForPage.getTotalPages()));
        return BaseResponseDto.success(articleData);
    }


    @ApiOperation("获取单个文章接口")
    @GetMapping("/getArticleById")
    public BaseResponseDto getArticleById(String id) {
        Article oneArticle = articleServerImp.getOneArticle(id);
        if (null != oneArticle.getAuther()) {
            oneArticle.setAuther(memberService.getMemberById(oneArticle.getAuther().getUsername()));
        }
        ArticleContent byArticleId = articleServerImp.findContentByArticleId(id);
        oneArticle.setContent(byArticleId.getContent());
        return BaseResponseDto.success(oneArticle);
    }


    @ApiOperation("删除文章接口")
    @PostMapping("/deleteArticle")
    public BaseResponseDto deleteArticle(String article, String getUserName) {

        Article byArticleId = articleServerImp.findByArticleId(article);
        if (null != article && null != byArticleId.getAuther()) {
            if (byArticleId.getAuther().getUsername().equals(getUserName)) {
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


    @ApiOperation("获取文章内容的地址")
    @GetMapping("/{id}/html")
    public String getArticleContentById(@PathVariable(name = "id") String id) {
        ArticleContent byArticleId = articleServerImp.findContentByArticleId(id);
        return byArticleId.getContent();
    }


}
