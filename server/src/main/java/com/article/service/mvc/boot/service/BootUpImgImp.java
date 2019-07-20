package com.article.service.mvc.boot.service;

import com.article.service.mvc.boot.entity.BootUpImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class BootUpImgImp {

    @Autowired
    private BootUpImgRepository bootUpImgRepository;


    /**
     * 修改状态
     *
     * @param bootUpImg
     * @return
     */
    public BootUpImg save(BootUpImg bootUpImg) {

        return bootUpImgRepository.save(bootUpImg);
    }

    /**
     * 查找所有
     *
     * @param status
     * @return
     */
    public List<BootUpImg> getbootImg(int status) {
        return bootUpImgRepository.findAllByStatus(status);
    }


    /**
     * 随机查找一个推送给前端手机
     *
     * @param status
     * @return
     */
    public BootUpImg getOneImg(int status) {
        List<BootUpImg> bootUpImgs = getbootImg(status);
        if (bootUpImgs.size() > 0) {
            int ints = new Random().nextInt(bootUpImgs.size());
            return bootUpImgs.get(ints);
        } else return null;

    }


    /**
     * 插入一条启动页记录
     *
     * @param img
     * @return
     */
    public BootUpImg insert(BootUpImg img) {
        try {
            BootUpImg insert = bootUpImgRepository.insert(img);
            return insert;
        } catch (Exception e) {
            return null;
        }
    }
}
