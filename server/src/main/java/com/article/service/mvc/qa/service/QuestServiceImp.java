package com.article.service.mvc.qa.service;

import com.article.service.mvc.qa.entity.Quest;
import com.article.service.mvc.qa.entity.QuestContent;
import com.article.service.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class QuestServiceImp {


    private QuestRepository questRepository;

    private QuestContentRepository questContentRepository;

    @Autowired
    public void setQuestContentRepository(QuestContentRepository questContentRepository) {
        this.questContentRepository = questContentRepository;
    }

    @Autowired
    public void setQuestRepository(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    /**
     * @author 保存文章信息
     * @serialData 19-7-20 - 下午2:03
     */
    public Quest save(Quest quest, String content) {
        String id = String.valueOf(new IdWorker().nextId());
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


    public Quest getQuestById(String id) {
        QuestContent byQuestContentId = questContentRepository.findByQuestContentId(id);
        Quest quest = questRepository.findByQuestId(id);
        if (quest != null && byQuestContentId != null) {
            quest.setContent(byQuestContentId.getContent());
        }
        return quest;
    }

}
