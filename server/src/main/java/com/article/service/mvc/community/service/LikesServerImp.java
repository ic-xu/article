package com.article.service.mvc.community.service;

import com.article.service.mvc.community.entity.Likes;
import com.article.service.mvc.community.service.repository.LikesRepository;
import com.article.service.mvc.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LikesServerImp {

    @Autowired
    LikesRepository likesRepository;


    /**
     * 标注喜欢
     *
     * @param likes
     * @return
     */
    public String save(Likes likes) {
        likes.setId(likes.getUserId() + likes.getArticleId());
        try {
            likesRepository.insert(likes);
            return "已添加至喜欢";
        } catch (Exception e) {
            return delete(likes);
        }
    }

    /**
     * 标注喜欢
     *
     * @param likes
     * @return
     */
    public String delete(Likes likes) {
        likes.setId(likes.getUserId() + likes.getArticleId());
        likesRepository.deleteById(likes.getId());
//        likesRepository.deleteById();
        return "已取消喜欢";
    }



    /**
     * 标注喜欢
     *
     * @param articleId
     * @return
     */
    public Long countByArticleId(String articleId) {
        return likesRepository.countByArticleId(articleId);
    }


    public List<Article> getLists(List<Article> likes, String userId) {

        List<Article> articles = new ArrayList<>();
        articles.addAll(likes);

        for (int i = 0; i < articles.size(); i++) {
            Likes oneByUserIdAndArticleId = likesRepository.findOneByUserIdAndArticleId(userId, articles.get(i).getArticleId());
            if (null != oneByUserIdAndArticleId)
                articles.get(i).setLike(true);
            else articles.get(i).setLike(false);
        }


        return articles;
    }

}
