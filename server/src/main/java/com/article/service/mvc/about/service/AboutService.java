package com.article.service.mvc.about.service;

import com.article.service.mvc.about.entity.About;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AboutService {

    @Autowired
    AboutRepository aboutRepository;


    /**
     * 后台编辑 about us
     *
     * @param about
     * @return
     */
    public About save(About about) {
        return aboutRepository.save(about);
    }


    public About find() {
        return aboutRepository.findAboutById("about");
    }
}
