package com.article.service.mvc.route.service;

import com.article.service.mvc.route.bean.ChatReqVO;

/**
 * Function: 账户服务
 */
public interface AccountService {


    /**
     * 推送消息
     *
     * @param url        url
     * @param groupReqVO 消息
     * @param sendUserId 发送者的ID
     * @throws Exception 异常
     */
    void pushMsg(String url, long sendUserId, ChatReqVO groupReqVO) throws Exception;


    String getServiceOnlneCount(String url);

}
