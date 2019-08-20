package com.admin.cx.task;


import com.admin.cx.SpringBootBeanUtil;
import com.admin.cx.pojo.EmailSendAndRev;
import com.admin.cx.utils.TimeUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by ubuntu
 * On 19-7-10 下午4:48
 */

public class SendEmailTask implements Runnable {

    String apiName;
    String type;
    int errorNumber;


    public SendEmailTask(String apiName, String type, int errorNumber) {
        this.apiName = apiName;
        this.type = type;
        this.errorNumber = errorNumber;
    }

    @Override
    public void run() {
        JavaMailSender javaMailSender = (JavaMailSender) SpringBootBeanUtil.getBean("javaMailSender");
        EmailSendAndRev emailSendAndRev = (EmailSendAndRev) SpringBootBeanUtil.getBean("emailSendAndRev");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSendAndRev.getSender());
        message.setTo(emailSendAndRev.getReceiver());
        message.setSubject("service " + apiName + "is error emile");// 标题
        message.setText(apiName + " is error \n type is " + type + "\n happdenCount is " + errorNumber + "\n happenTime is " + TimeUtils.getCuurentTime());// 内容
        try {
            javaMailSender.send(message);
            System.err.println("文本邮件发送成功！");
        } catch (Exception e) {
            System.err.println("文本邮件发送异常！\n" + e.getMessage());
        }


    }
}
