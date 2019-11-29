package com.common.mvc.qa.service;

import com.common.mvc.qa.entity.Answer;
import com.common.mvc.qa.entity.Quest;
import com.common.mvc.qa.entity.QuestClassify;
import com.common.mvc.qa.entity.QuestContent;
import com.common.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

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

    @Autowired
    private AnswerRepository answerRepository;

    private ReentrantLock reentrantLock = new ReentrantLock(true);


    /**
     * @apiNote 保存问题
     */
    public Quest save(Quest quest) {
        String id = String.valueOf(IdWorker.getInstance().nextId());
        quest.setQuestId(id);
        questContentRepository.save(new QuestContent(id, quest.getContent()));
        String describe = quest.getDescribe();
        if (null == describe)
            if(quest.getContent().length()<=20)
                quest.setDescribe(quest.getContent());
            else
                quest.setDescribe(quest.getContent().substring(0,20));
        quest.setContent(null);
        return questRepository.save(quest);
    }



    /**
     * @apiNote 保存问题
     */
    public QuestClassify save(QuestClassify questClassify) {
        questClassify.setId(IdWorker.getInstance().nextId());
        return questClassifyRepository.save(questClassify);
    }



    /**
     * @author 获取文章信息
     * @serialData 19-7-20 - 下午2:03
     */
    public Page<Quest> getQuest(String classify,PageRequest pageRequest) {
        if(null==classify)
            return questRepository.findAll(pageRequest);
        return questRepository.findAllByQuestClassify(classify,pageRequest);
    }


    /**
     * @apiNote 获取问题详细描述
     */
    public Quest getQuestById(String id) {
        QuestContent byQuestContentId = questContentRepository.findByQuestContentId(id);

        reentrantLock.lock();
        Quest quest = questRepository.findByQuestId(id);
        quest.setReadCount(quest.getReadCount() + 1);
        questRepository.save(quest);
        reentrantLock.unlock();

        Quest quest1 = new Quest();
        BeanUtils.copyProperties(quest, quest1);
        if (byQuestContentId != null) {
            quest1.setContent(byQuestContentId.getContent());
        }
        return quest1;
    }


    /**
     * @apiNote 获取所有的分类列表
     */
    public List<QuestClassify> getAllClassify() {

        return questClassifyRepository.findAll();
    }


    /**
     * @apiNote 查找问题答案
     */
    public Page<Answer> getAnswers(String questId, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "agreeCount"));
        return answerRepository.findAllByQuestId(questId, pageRequest);
    }


    /**
     * @apiNote 保存问题答案并更新问题数量
     */
    public synchronized void SaveAnswers(Answer answer) {
        answerRepository.save(answer);
        Quest quest = questRepository.findByQuestId(answer.getQuestId());
        quest.setAnswerCount(quest.getAnswerCount() + 1);
        questRepository.save(quest);
    }


}
