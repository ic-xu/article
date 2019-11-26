package com.common.mvc.qa.service;

import com.common.mvc.qa.entity.Quest;
import com.common.mvc.qa.entity.QuestClassify;
import com.common.mvc.qa.entity.QuestContent;
import com.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenxu
 * @serialData 19-7-20 - 下午2:03
 */
@Service
public class QuestServiceImp {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private QuestContentRepository questContentRepository;

    @Autowired
    private QuestClassifyRepository questClassifyRepository;



    /**
    * @apiNote 保存问题
    */
    public Quest save(Quest quest, String content) {
        String id = String.valueOf(IdWorker.getInstance().nextId());
        quest.setQuestId(id);
        questContentRepository.save(new QuestContent(id, content));
        return questRepository.save(quest);
    }


    /**
     * @author 获取文章信息
     * @serialData 19-7-20 - 下午2:03
     */
    public Page<Quest> getQuest(PageRequest pageRequest) {
        return questRepository.findAll(pageRequest);
    }


    /**
    * @apiNote 获取问题详细描述
    */
    public Quest getQuestById(String id) {
        QuestContent byQuestContentId = questContentRepository.findByQuestContentId(id);
        Quest quest = questRepository.findByQuestId(id);
        if (quest != null && byQuestContentId != null) {
            quest.setContent(byQuestContentId.getContent());
        }
        return quest;
    }


    /**
    * @apiNote  获取所有的分类列表
    */
    public List<QuestClassify> getAllClassify(){

        return questClassifyRepository.findAll();
    }

}
