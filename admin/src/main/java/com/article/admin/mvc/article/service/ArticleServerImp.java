package com.article.admin.mvc.article.service;

import com.article.admin.utils.IdWorker;
import com.article.admin.mvc.article.entity.Article;
import com.article.admin.mvc.article.entity.ArticleContent;
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

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setArticleContentRepository(ArticleContentRepository articleContentRepository) {
        this.articleContentRepository = articleContentRepository;
    }

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }


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
     * 根据状态查找文章
     *
     * @return page对象
     */
    public Page findAllByStatus(int status, PageRequest pageRequest) {
        if (status < 0)
            return articleRepository.findAll(pageRequest);
        else
            return articleRepository.findAllByStatus(status, pageRequest);
    }


    /**
     * 保存文章
     *
     */
    public boolean insertArticle(Article article) {
        article.setArticleId(new IdWorker().nextId() + "" + System.currentTimeMillis());
        article.setHappenTime(System.currentTimeMillis());
        articleRepository.save(article);
        return true;
    }


    /**
     * 根据标签查询列表
     *
     */
    public List<Article> getArticleForPage(int status, int page, int pageSize, String tags) {
        Query query = new Query();
        if (!"".equals(tags) && null != tags) {
            Pattern pattern = Pattern.compile("^.*" + tags + ".*$", Pattern.CASE_INSENSITIVE);
            if (status < 0)
                query.addCriteria(Criteria.where("tags").regex(pattern));
            else query.addCriteria(Criteria.where("status").is(status).and("tags").regex(pattern));
        }

        query.with(Sort.by(new Sort.Order(Sort.Direction.DESC, "happenTime")))
                .skip(page * pageSize).limit(pageSize);
        return mongoTemplate.find(query, Article.class, "article");
    }


    /**
     * 获取单个文章
     *
     */
    public Article getOneArticle(String id) {

        return articleRepository.findByArticleId(id);
    }

    /**
     * 获取文章数量
     *
     */
    public long getCount() {

        return articleRepository.count();
    }


    public void delete(String articleId) {
        articleRepository.deleteById(articleId);
        articleContentRepository.deleteById(articleId);
    }

}
