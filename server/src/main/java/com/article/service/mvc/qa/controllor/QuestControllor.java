package com.article.service.mvc.qa.controllor;

import com.article.service.mvc.qa.entity.Quest;
import com.article.service.mvc.qa.service.QuestServiceImp;
import com.article.service.utils.BaseGetData;
import com.article.service.utils.BaseResponseDto;
import com.article.service.utils.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


/**
 * Created by ubuntu
 * On 19-7-20 下午1:49
 */

@RestController
@RequestMapping("/questControllor")
@Api(tags = "问答相关")
public class QuestControllor {


    private QuestServiceImp questServiceImp;

    @Autowired
    public void setQuestServiceImp(QuestServiceImp questServiceImp) {
        this.questServiceImp = questServiceImp;
    }


    @ApiOperation("保存文章")
    @PostMapping("/save")
    @ApiImplicitParam(name = "content", value = "文章内容", required = true)
    public BaseResponseDto save(Quest quest, String content) {
        quest.setConllection(false);
        quest.setHappenTIme(System.currentTimeMillis());
        quest.setHot(false);
        quest.setQuestId(new IdWorker().nextId() + "");
        Quest save = questServiceImp.save(quest, content);
        return BaseResponseDto.success(save);
    }

    @ApiOperation("获取问答列表")
    @GetMapping("/getQuest/{page}")
    public BaseResponseDto getQuest(@PathVariable("page") int page) {

        PageRequest pageRequest = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "happenTIme"));
        Page<Quest> quest = questServiceImp.getQuest(pageRequest);
        return BaseResponseDto.success(new BaseGetData<>(page, quest.getContent(), quest.getTotalPages()));
    }

    @ApiOperation("查看问题详情")
    @GetMapping("/getContent/{id}")
    public BaseResponseDto getContent(@PathVariable("id") String id) {
        return BaseResponseDto.success(questServiceImp.getQuestById(id));
    }

}
