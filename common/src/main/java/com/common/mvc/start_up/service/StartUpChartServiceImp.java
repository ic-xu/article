package com.common.mvc.start_up.service;

import com.common.mvc.start_up.entity.StartUpChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StartUpChartServiceImp {

    @Autowired
    StartUpChartRepository startUpChartRepository;


    /**
     * 新增记录
     * @param startUpChart
     * @return
     */
    public StartUpChart save(StartUpChart startUpChart){
        return startUpChartRepository.save(startUpChart);
    }



    /**
     * 根据id删除记录
     * @param imgUrl
     */
    public void delete(String imgUrl){
         startUpChartRepository.deleteById(imgUrl);
    }
}
