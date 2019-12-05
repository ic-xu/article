package com.common.mvc.creative.service;


import com.common.mvc.creative.entity.Creative;
import com.common.mvc.creative.entity.CreativeClassify;
import com.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenxu
 */
@Service
public class CreativeService {

    @Autowired
    private CreativeRepository creativeRepository;

    @Autowired
    private CreativeClassifyRepository creativeClassifyRepository;


    /*
        保存分类信息
     */
    public void saveCreativeClassify(String classify) {
        CreativeClassify creativeClassify = new CreativeClassify();
        creativeClassify.setClassify(classify);
        creativeClassifyRepository.save(creativeClassify);
    }

    /*
        获取分类列表
     */
    public List<CreativeClassify> findAllClassify() {
        return creativeClassifyRepository.findAll();
    }

    /*
    保存创意信息
     */
    public Creative saveCreative(Creative creative) {
        return creativeRepository.save(creative);
    }

    /*
   获取创意列表
    */
    public Page<Creative> findAllByStatus(int status, String classify, PageRequest pageRequest) {
        if (null == classify || "全部".equalsIgnoreCase(classify))
            return creativeRepository.findAllByStatus(status, pageRequest);
        return creativeRepository.findAllByStatusAndClassify(status, classify, pageRequest);
    }
}
