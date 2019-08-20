package com.admin.cx;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * Created by ubuntu
 * On 19-7-9 下午8:13
 */

@Component
public class SpringBootBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

}
