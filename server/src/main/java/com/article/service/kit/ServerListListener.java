package com.article.service.kit;

import com.article.service.config.AppConfiguration;
import com.common.utils.SpringBeanFactory;

public class ServerListListener implements Runnable{

    private ZKit zkUtil;

    private AppConfiguration appConfiguration ;


    public ServerListListener() {
        zkUtil = SpringBeanFactory.getBean(ZKit.class) ;
        appConfiguration = SpringBeanFactory.getBean(AppConfiguration.class) ;
    }

    @Override
    public void run() {
        //注册监听服务
        zkUtil.subscribeEvent(appConfiguration.getZkRoot());

    }
}
