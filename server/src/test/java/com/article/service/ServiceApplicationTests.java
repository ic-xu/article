package com.article.service;

import com.common.mvc.creative.service.CreativeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceApplicationTests {

    @Autowired
    CreativeService creativeService;

    @Test
    public void contextLoads() {
        creativeService.saveCreativeClassify("餐饮");
        creativeService.saveCreativeClassify("测试创意");
    }

}
