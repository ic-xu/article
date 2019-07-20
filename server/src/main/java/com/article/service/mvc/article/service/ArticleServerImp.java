package com.article.service.mvc.article.service;

import com.article.service.mvc.community.service.LikesServerImp;
import com.article.service.utils.IdWorker;
import com.article.service.mvc.article.entity.Article;
import com.article.service.mvc.article.entity.ArticleContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ArticleServerImp {

    private ArticleRepository articleRepository;

    private ArticleContentRepository articleContentRepository;

    private LikesServerImp likesServerImp;

    @Autowired
    public void setArticleContentRepository(ArticleContentRepository articleContentRepository) {
        this.articleContentRepository = articleContentRepository;
    }

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Autowired
    public void setLikesServerImp(LikesServerImp likesServerImp) {
        this.likesServerImp = likesServerImp;
    }


    public Article save(Article article) {
        return articleRepository.save(article);
    }

    /**
     * 保存文章
     */
    public boolean insertArticle(Article article) {
        article.setArticleId(new IdWorker().nextId() + "" + System.currentTimeMillis());
        article.setHappenTime(System.currentTimeMillis());
        articleRepository.save(article);
        return true;
    }

//    public List<Article> getAllArticle(int page, int pageSize, String tags) {
//        Pattern pattern = Pattern.compile("^.*" + tags + ".*$", Pattern.CASE_INSENSITIVE);
//        Query query = new Query(Criteria.where("status").is(1).and("tags").regex(pattern))
//                .with(Sort.by(new Sort.Order(Sort.Direction.DESC, "happenTime")))
//                .skip(page * pageSize).limit(pageSize);
//        return mongoTemplate.find(query, Article.class, "article");
//    }


    public void saveContent(ArticleContent articleContent) {
        articleContentRepository.save(articleContent);
    }


    /**
     * 根据ID查找
     */
    public Article findByArticleId(String articleId) {
        return articleRepository.findByArticleId(articleId);
    }


    /**
     * 根据ID查找内容
     */
    public ArticleContent findContentByArticleId(String articleId) {
        return articleContentRepository.findByArticleId(articleId);
    }


    /**
     * 根据标签查询列表
     */
    public Page<Article> getArticleForPage(int page, int pageSize, String classify, int status) {

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "happenTime"));

        return articleRepository.findByTagsAndClassify(classify, status, pageRequest);

//        Query query = new Query();
//        if (!"".equals(tags) && null != tags) {
//            Pattern pattern = Pattern.compile("^.*" + tags + ".*$", Pattern.CASE_INSENSITIVE);
//            query.addCriteria(Criteria.where("status").is(1).and("tags").regex(pattern));
//        }
//        query.with(Sort.by(new Sort.Order(Sort.Direction.DESC, "happenTime")))
//                .skip(page * pageSize).limit(pageSize);
//        List<Article> article = mongoTemplate.find(query, Article.class, "article");
//
//        article.forEach(art -> art.setLikeCount(likesServerImp.countByArticleId(art.getArticleId())));

//        return article;
    }


    /**
     * 获取单个文章
     */
    public Article getOneArticle(String id) {

        return articleRepository.findByArticleId(id);
    }


    public void delete(String articleId) {
        articleRepository.deleteById(articleId);
        articleContentRepository.deleteById(articleId);
    }

}
