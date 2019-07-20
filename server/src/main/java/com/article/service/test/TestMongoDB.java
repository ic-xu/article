//package com.tcl.community.test;
//
//
//import com.tcl.community.mvc.article.entity.Article;
//import com.tcl.community.mvc.community.entity.Comment;
//import com.tcl.community.mvc.article.service.ArticleServerImp;
//import com.tcl.community.mvc.community.service.CommentServiceImp;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Api
//@RestController
//public class TestMongoDB {
//
//    @Autowired
//    private ArticleServerImp articleServerImp;
//
//    @Autowired
//    static CommentServiceImp commentServiceImp;
//
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @GetMapping("/getTest")
//    @ApiOperation("getTest")
//    public String getTest() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
//
//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//        while (true) {
//
//
//            String articleId = simpleDateFormat1.format(new Date());
//            String commentId = simpleDateFormat.format(new Date());
//
//            Article article = new Article();
//            try {
//                articleServerImp.save(article);
//            }catch (Exception e){
//                System.err.println(e.getMessage());
//            }
//
//            Comment comment = new Comment(commentId, "测试评论，时间是" + simpleDateFormat.format(new Date()),
//                    System.currentTimeMillis(), "测试用户", "测试用户名", "测试照片", articleId);
//
//            try {
//                mongoTemplate.save(comment);
//            }catch (Exception e){
//                System.err.println(e.getMessage());
//            }
//
//        }
//
//    }
//}
