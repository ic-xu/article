package com.common.mvc.banner.service;

import com.common.mvc.banner.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerService {

    @Autowired
    BannerRepository bannerRepository;


    /**
     * 获取轮播图
     *
     * @param status 状态为可显示的轮播图
     * @return List<Banner>
     */
    public List<Banner> findAll(int status) {
        if(status<0)
            return bannerRepository.findAll();
        return bannerRepository.findAllByStatus(status);
    }

    /**
     * 修改轮播图
     *
     * @param banner
     * @return
     */
    public Banner save(Banner banner) {
        return bannerRepository.save(banner);
    }

    /**
     * 新增轮播图
     *
     * @param banner
     * @return
     */
    public Banner insert(Banner banner) {
        try {
            return bannerRepository.insert(banner);
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * 新增轮播图
     *
     * @param id
     * @return
     */
    public void delete(String id) {
        try {
             bannerRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("=====>>>> 删除失败"+e.getMessage());
        }

    }

}
