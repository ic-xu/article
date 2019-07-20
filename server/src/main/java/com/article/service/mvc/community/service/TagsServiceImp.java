package com.article.service.mvc.community.service;

import com.article.service.mvc.community.entity.Tags;
import com.article.service.mvc.community.service.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagsServiceImp {

    @Autowired
    TagsRepository tagsRepository;

    /**
     * 保存标签
     * @param tags
     * @return
     */
    public Tags saveTypeOne(Tags tags) {
        try {
            return tagsRepository.insert(tags);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


    /**
     * 默认一级分类就是标签
     * @return
     */
    public List<Tags> getAllTargs() {
        return tagsRepository.findAll();
    }
}
