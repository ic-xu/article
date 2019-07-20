package com.article.service.mvc.article.service;

import com.article.service.mvc.community.service.LikesServerImp;
import com.article.service.utils.IdWorker;
import com.article.service.mvc.article.entity.Article;
import com.article.service.mvc.article.entity.ArticleContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ArticleServerImp {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleContentRepository articleContentRepository;

    @Autowired
    LikesServerImp likesServerImp;

    @Autowired
    MongoTemplate mongoTemplate;

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    /**
     * 保存文章
     *
     * @param article
     * @return
     */
    public boolean insertArticle(Article article) {
        article.setArticleId(new IdWorker().nextId() + "" + System.currentTimeMillis());
        article.setHappenTime(System.currentTimeMillis());
        return articleRepository.save(article) == null ? false : true;
    }

    public List<Article> getAllArticle(int page, int pageSize, String tags) {
        Pattern pattern = Pattern.compile("^.*" + tags + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = new Query(Criteria.where("status").is(1).and("tags").regex(pattern))
                .with(new Sort(new Sort.Order(Sort.Direction.DESC, "happenTime")))
                .skip(page * pageSize).limit(pageSize);
        List<Article> article = mongoTemplate.find(query, Article.class, "article");
        return article;
    }



    public ArticleContent saveContent(ArticleContent articleContent){
        return articleContentRepository.save(articleContent);
    }


    /**
     * 根据ID查找
     * @param articleId
     * @return
     */
    public Article findByArticleId(String articleId){
        return articleRepository.findByArticleId(articleId);
    }


    /**
     * 根据ID查找内容
     * @param articleId
     * @return
     */
    public ArticleContent findContentByArticleId(String articleId){
        return articleContentRepository.findByArticleId(articleId);
    }



    /**
     * 根据标签查询列表
     *
     * @param page
     * @param pageSize
     * @param tags
     * @return
     */
    public List<Article> getArticleForPage(int page, int pageSize, String tags) {
        Query query = new Query();
        if (!"".equals(tags) && null != tags) {
            Pattern pattern = Pattern.compile("^.*" + tags + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("status").is(1).and("tags").regex(pattern));
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "happenTime")))
                .skip(page * pageSize).limit(pageSize);
        List<Article> article = mongoTemplate.find(query, Article.class, "article");

        article.forEach(art -> art.setLikeCount(likesServerImp.countByArticleId(art.getArticleId())));

        return article;
    }


    /**
     * 获取单个文章
     *
     * @param id
     * @return
     */
    public Article getOneArticle(String id) {

        return articleRepository.findByArticleId(id);
    }

    /**
     * 获取文章数量
     *
     * @return
     */
    public long getCount() {

        return articleRepository.count();
    }


    public void delete(String articleId){
        articleRepository.deleteById(articleId);
        articleContentRepository.deleteById(articleId);
    }

}
