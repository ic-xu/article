package com.common.mvc.msghistory.service;

import com.common.mvc.msghistory.entity.MessageHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenxu
 * On 19-8-28 下午5:28
 */
@Repository("messageService")
public class MessageHistoryService {

    @Autowired
    private MessageHistoryRepository messageHistoryRepository;



    /**
     * @apiNote 历史消息记录
     */
    public List<MessageHistory> findById(String id) {
        List<MessageHistory> all = messageHistoryRepository.findAllById(id);
        System.err.println("------------------------------------------------");
        System.err.println(all);
        System.err.println(all.toString());
        return all;
    }

}
