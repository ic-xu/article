package com.article.service.mvc.article;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.common.mvc.article.entity.Comment;
import com.common.mvc.article.entity.ReplyComment;
import com.common.mvc.article.service.CommentService;
import com.common.mvc.article.service.ReplyCommentService;
import com.common.mvc.user.entity.Member;
import com.common.utils.BaseResponseDto;
import com.common.utils.IdWorker;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "评论和回复相关")
@RequestMapping("/comment")
public class CommentControllor {

    @Autowired
    private ReplyCommentService replyCommentService;

    @Autowired
    private CommentService commentService;


    /**
     * 保存留言
     *
     * @param articleId id
     * @param content con
     * @param userId user
     * @return fd
     */
    @PostMapping("/save")
    @ApiOperation("保存评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章id", required = true),
            @ApiImplicitParam(name = "content", value = "留言内容", required = true),
            @ApiImplicitParam(name = "userId", value = "留言用户Id", required = true)
    })
    public BaseResponseDto save(String articleId, String content, String userId) {
        Comment comment = new Comment();
        comment.setCreateTime(System.currentTimeMillis());
        comment.setArticleId(articleId);
        comment.setCommentContent(content);
        Member member = new Member();
        comment.setMember(member);
        comment.setCommentId("C" + IdWorker.getInstance().nextId());
        commentService.save(comment);
        return BaseResponseDto.success();
    }


    /**
     * 保存回复
     *
     * @param articleId fd
     * @param content dfd
     * @param userId dfd
     * @param toUserId fd
     * @return fd
     */
    @PostMapping("/saveReplay")
    @ApiOperation("保存回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章id", required = true),
            @ApiImplicitParam(name = "content", value = "留言内容", required = true),
            @ApiImplicitParam(name = "userId", value = "留言用户Id", required = true),
            @ApiImplicitParam(name = "toUserId", value = "回复对象的Id", required = true)
    })
    public BaseResponseDto saveReplay(String articleId, String content, String userId, String toUserId) {
        ReplyComment replyComment = new ReplyComment();
        replyComment.setCreateTime(System.currentTimeMillis());
        replyComment.setArticleId(articleId);
        replyComment.setCommentContent(content);
        Member member = new Member();
        replyComment.setMember(member);
        replyComment.setCommentId("C" + IdWorker.getInstance());
        replyComment.setToMemberId(toUserId);
        replyCommentService.save(replyComment);
        return BaseResponseDto.success();
    }


    /**
     * 删除留言或回复
     *
     * @param commentId df
     * @return df
     */
    @PostMapping("/deleteComment")
    @ApiOperation("删除留言、回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId", value = "删除回复或者留言的唯一表示", required = true)
    })
    public BaseResponseDto deleteComment(String commentId) {

        replyCommentService.deleteById(commentId);
        Comment byId = commentService.findById(commentId);
        if (null != byId.getMember() && null != byId.getMember().getUsername())
            replyCommentService.deleteAllByToMemberId(byId.getMember().getUsername());
        commentService.deleteById(commentId);
        return BaseResponseDto.success();
    }


    /**
     * 获取留言
     *
     * @param articleId df
     * @return dfd
     */
    @GetMapping("/getAllByArticleId")
    @ApiOperation("获取留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章的唯一标示", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每一页的大小，默认20", defaultValue = "20", required = true),
            @ApiImplicitParam(name = "pageNumber", value = "标示文章是当前的第几页，默认0", defaultValue = "0", required = true)
    })
    public BaseResponseDto getAllByArticleId(String articleId,
                                             @RequestParam(defaultValue = "20", name = "pageSize") int pageSize,
                                             @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("createTime")));
        Page<Comment> allByArticleId = commentService.findAllByArticleId(articleId, pageRequest);
        List<Object> all = new ArrayList<>();
        allByArticleId.getContent().forEach(mb -> {
            Comment comment = mb;
            all.add(comment);
            String userId = comment.getMember().getUsername();
            if (null != comment.getMember() && null != comment.getMember().getUsername()) {
                Page<ReplyComment> allByArticleIdAndToMemberId = replyCommentService.findAllByArticleIdAndToMemberId(articleId,
                        userId, pageRequest);
                all.addAll(allByArticleIdAndToMemberId.getContent());
            }
        });
        return BaseResponseDto.success(all);
    }


    /**
     * 获取某一留言的回复
     *
     * @param articleId 文章id
     * @return list
     */
    @GetMapping("/getAllReplyByArticleId")
    @ApiOperation("获取回复记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章的唯一标示", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每一页的大小，默认20", defaultValue = "20", required = true),
            @ApiImplicitParam(name = "pageNumber", value = "标示文章是当前的第几页，默认0", defaultValue = "0", required = true)
    })
    public BaseResponseDto getAllReplyByArticleId(String articleId, String toUserId,
                                                  @RequestParam(defaultValue = "20", name = "pageSize") int pageSize,
                                                  @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
        if (pageNumber < 0)
            pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("createTime")));
        Page<ReplyComment> allByArticleIdAndToMemberId = replyCommentService.findAllByArticleIdAndToMemberId(articleId,
                toUserId, pageRequest);
        return BaseResponseDto.success(allByArticleIdAndToMemberId.getContent());
    }


}
