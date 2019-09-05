package com.admin.cx.controllor;


import com.admin.cx.task.ReadTask;
import com.admin.cx.task.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ubuntu
 * On 19-7-9 下午6:53
 */

@RestController
//@RequestMapping("启动监控任务")
public class IndexControllor {

    @Value("${spring.boot.admin.notify.mail.from}")
    private String sender;

    @Value("${spring.boot.admin.notify.mail.to}")
    private String receiver;

    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * @Description http://localhost:8888/sendMail
     * @method 发送文本邮件
     * @return
     */
    @RequestMapping("/sendMail")
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject("这个是IndexControllor发出的");// 标题
        message.setText("世界，你好！--->文本邮件");// 内容
        try {
            javaMailSender.send(message);
            return "文本邮件发送成功！";
        } catch (Exception e) {
            return "文本邮件发送异常！ ==>"+ e;
        }
    }




    @GetMapping("/startTask")
    public String startTask(String url) {

        if (null == url)
            return "地址不能为空";
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            return "地址必须以http://或者必须以https://开头";
        if (!url.endsWith("/hystrix.stream"))
            return "地址必须是/hystrix.stream结尾的可访问地址";
        if (testUrlWithTimeOut(url, 5000)) {
            TaskManager.submit(new ReadTask(url),url);
        }
        return "执行成功";
    }

    public boolean testUrlWithTimeOut(String urlString, int timeOutMillSeconds) {
        long lo = System.currentTimeMillis();
        URL url;
        try {
            url = new URL(urlString);
            URLConnection co = url.openConnection();
            co.setConnectTimeout(timeOutMillSeconds);
            co.connect();
            if (System.currentTimeMillis() - lo < timeOutMillSeconds)
                return true;
            return false;
        } catch (Exception e1) {
            System.out.println("连接打不开!");
            return false;
        }
    }
}
