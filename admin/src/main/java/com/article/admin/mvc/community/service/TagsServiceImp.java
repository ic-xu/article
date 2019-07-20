package com.article.admin.mvc.community.service;

import com.article.admin.mvc.community.entity.Tags;
import com.article.admin.mvc.community.service.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagsServiceImp {

    @Autowired
    TagsRepository tagsRepository;

    /**
     * 保存标签
     *
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
     *获取所有
     *
     * @return
     */
    public List<Tags> getAllTargs() {

        return tagsRepository.findAll();
    }


    /**
     * 删除一级分类
     *
     * @return
     */
    public void delete(String tagsName) {
        tagsRepository.deleteByTagsName(tagsName);
    }



    /**
     * 修改标签
     *
     * @param tags
     * @return
     */
    public Tags updata(Tags tags) {
        try {
            return tagsRepository.save(tags);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
