package com.admin.cx.task;


import com.admin.cx.pojo.TBean;
import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by ubuntu
 * On 19-7-9 下午7:13
 */
@SuppressWarnings("ALL")
public class ReadTask implements Runnable {

    private String url;


    public ReadTask(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        InputStream openStream = null;
        try {
            Thread.sleep(20000);
            URL url = new URL(this.url);
            openStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(openStream));
            String line = null;
            TBean tBean = null;
            while (true) {
                try {
                    line = reader.readLine();
                    if (line.startsWith("data:")) {
                        tBean = JSON.parseObject(line.split(" ")[1], TBean.class);
//                        RedisUtil.getInstance().hset(this.url, "ExecutionCount", tBean.getCurrentConcurrentExecutionCount());
//                        RedisUtil.getInstance().hset(this.url, "RollingCountBadRequests", tBean.getRollingCountBadRequests());
//                        RedisUtil.getInstance().hset(this.url, "CurrentConcurrentExecutionCount", tBean.getCurrentConcurrentExecutionCount());
//                        RedisUtil.getInstance().hset(this.url, "RollingCountFallbackSuccess", tBean.getRollingCountFallbackSuccess());
//                        RedisUtil.getInstance().hset(this.url, "ErrorCount", tBean.getErrorCount());

                        if (tBean.getRollingCountBadRequests() == 5) {
                            TaskManager.sendEmilJog(new SendEmailTask(tBean.getName(), "RollingCountBadRequests", tBean.getRollingCountBadRequests()));
                        }
                        if (tBean.getCurrentConcurrentExecutionCount() == 5) {
                            TaskManager.sendEmilJog(new SendEmailTask(tBean.getName(), "CurrentConcurrentExecutionCount", tBean.getCurrentConcurrentExecutionCount()));
                        }

                        if (tBean.getRollingCountFallbackSuccess() == 5) {
                            TaskManager.sendEmilJog(new SendEmailTask(tBean.getName(), "RollingCountFallbackSuccess", tBean.getRollingCountFallbackSuccess()));
                        }
                        if (tBean.getErrorCount() > 0) {
                            TaskManager.sendEmilJog(new SendEmailTask(tBean.getName(), "ErrorCount", tBean.getErrorCount()));
                        }

                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
