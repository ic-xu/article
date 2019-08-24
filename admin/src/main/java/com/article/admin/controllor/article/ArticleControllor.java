package com.article.admin.controllor.article;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.common.mvc.article.entity.Article;
import com.common.mvc.article.entity.ArticleContent;
import com.common.mvc.article.service.ArticleServerImp;
import com.common.mvc.comment.service.CommentService;
import com.common.mvc.comment.service.ReplyCommentService;
import com.common.mvc.member.entity.Member;
import com.common.mvc.member.service.MemberService;
import com.common.utils.BaseResponseDto;
import com.common.utils.IdWorker;

import java.util.List;


@RestController
@Api(tags = "文章相关接口")
@RequestMapping("/article")
public class ArticleControllor {

    private MemberService memberService;

    private ArticleServerImp articleServerImp;

    private ReplyCommentService replyCommentService;

    private CommentService commentService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Autowired
    public void setReplyCommentService(ReplyCommentService replyCommentService) {
        this.replyCommentService = replyCommentService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setArticleServerImp(ArticleServerImp articleServerImp) {
        this.articleServerImp = articleServerImp;
    }

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
        article.setId("A" +IdWorker.getInstance().nextId());
        article.setArticleTitle(articleTitle);
        article.setDesc(articleDescribe);
        article.setHappenTime(System.currentTimeMillis());
        Member member = new Member();
        member.setUserId(userId);
        article.setAuther(member);
        article.setKeyWord(keyWord);
        article.setTags(tags);
        article.setLike(false);
        article.setStatus(1);
        article.setNewsImg(image);

        System.out.println(article);
        article.setStatus(1);
        if (articleServerImp.insertArticle(article)) {
            ArticleContent articleContent = new ArticleContent();
            articleContent.setArticleId(article.getId());
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
            @ApiImplicitParam(name = "tags", value = "标签", dataType = "string", defaultValue = " "),
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
        if (null != oneArticle.getArticleTitle()) {
            oneArticle.setAuther(memberService.getMemberById(oneArticle.getAuther().getUserId()));
        }
        ArticleContent byArticleId = articleServerImp.findContentByArticleId(id);
        oneArticle.setContent(byArticleId.getContent());
        return BaseResponseDto.success(oneArticle);
    }


    /**
     * 根据状态查找文章
     *
     * @param status     zhuangtai
     * @param pageSize   pageSize
     * @param pageNumber pageNumber
     * @return return
     */
    @ApiOperation("根据状态查找文章")
    @GetMapping("/findAllByStatus")
    public BaseResponseDto findAllByStatus(@RequestParam(defaultValue = "-1") int status,
                                           @RequestParam(defaultValue = "20") int pageSize,
                                           @RequestParam(defaultValue = "0") int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("happenTime")));
        return BaseResponseDto.success(articleServerImp.findAllByStatus(status, pageRequest));
    }


    /**
     * 审核文章
     *
     * @param articleId 文章Id
     */
    @ApiOperation("审核文章")
    @PostMapping("/statusChange")
    public BaseResponseDto statusChange(@RequestParam() String articleId) {

        Article oneArticle = articleServerImp.getOneArticle(articleId);
        oneArticle.setStatus(1);
        oneArticle.setHappenTime(System.currentTimeMillis());
        return BaseResponseDto.success(articleServerImp.save(oneArticle));
    }


    @ApiOperation("删除文章接口")
    @GetMapping("/deleteArticle")
    public BaseResponseDto deleteArticle(String article, String userId) {

        Article byArticleId = articleServerImp.findByArticleId(article);
        if (null != article && null != byArticleId.getAuther()) {
            if (byArticleId.getAuther().getUserId().equals(userId)) {
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
