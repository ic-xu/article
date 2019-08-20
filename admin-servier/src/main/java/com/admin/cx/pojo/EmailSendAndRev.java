package com.admin.cx.pojo;


/**
 * Created by ubuntu
 * On 19-7-10 下午6:18
 */

public class EmailSendAndRev {

    private String sender;
    private String receiver;


    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }
}
