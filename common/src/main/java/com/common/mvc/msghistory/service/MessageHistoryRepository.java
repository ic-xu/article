package com.common.mvc.msghistory.service;


import com.common.mvc.msghistory.entity.MessageHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by chenxu
 * On 19-8-28 下午5:29
 */

public interface MessageHistoryRepository extends MongoRepository<MessageHistory, String> {

    List<MessageHistory> findAllById(String id);
}
