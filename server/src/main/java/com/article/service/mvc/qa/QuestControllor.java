package com.article.service.mvc.qa;

import com.article.service.utils.BaseGetData;
import com.common.mvc.qa.entity.Answer;
import com.common.mvc.qa.entity.QuestClassify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.common.mvc.qa.entity.Quest;
import com.common.mvc.qa.service.QuestServiceImp;
import com.common.utils.BaseResponseDto;
import com.common.utils.IdWorker;


/**
 * Created by chenxu
 * On 19-7-20 下午1:49
 */

@RestController
@RequestMapping("quest/")
@Api(tags = "问答相关")
public class QuestControllor {


    private QuestServiceImp questServiceImp;

    @Autowired
    public void setQuestServiceImp(QuestServiceImp questServiceImp) {
        this.questServiceImp = questServiceImp;
    }


    @ApiOperation("保存问题信息")
    @PostMapping("save")
    @ApiImplicitParam(name = "content", value = "文章内容", required = true)
    public BaseResponseDto save(Quest quest) {
//        quest.setIsConllection(false);
        quest.setHappenTIme(System.currentTimeMillis());
//        quest.setIsHot(false);
        quest.setQuestId(IdWorker.getInstance().nextId() + "");
        Quest save = questServiceImp.save(quest);
        return BaseResponseDto.success(save);
    }

    @ApiOperation("获取问题列表")
    @GetMapping("{page}/{size}/json")
    public BaseResponseDto getQuest(@PathVariable("page") int page,@PathVariable("size") int size,@RequestParam(required = false) String classify) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "happenTIme"));
        Page<Quest> quest = questServiceImp.getQuest(classify,pageRequest);
        return BaseResponseDto.success(new BaseGetData<>(page, quest.getContent(), quest.getTotalPages()));
    }

    @ApiOperation("查看问题详情")
    @GetMapping("getContent/{id}")
    public BaseResponseDto getContent(@PathVariable("id") String id) {
        return BaseResponseDto.success(questServiceImp.getQuestById(id));
    }


    @ApiOperation("获取所有的分类列表")
    @GetMapping("classify/json")
    public BaseResponseDto getQuestClassify() {
        return BaseResponseDto.success(questServiceImp.getAllClassify());
    }



    @ApiOperation("保存分类列表")
    @GetMapping("classify/save")
    public BaseResponseDto saveClassify(QuestClassify questClassify) {
        return BaseResponseDto.success(questServiceImp.save(questClassify));
    }



    @ApiOperation("获取答案列表")
    @GetMapping("answer/{page}/{size}/json")
    public BaseResponseDto getQuestAnswer(String questId,@PathVariable("page") int page,@PathVariable("size") int size) {
        return BaseResponseDto.success(questServiceImp.getAnswers(questId,page,size));
    }

    @ApiOperation("保存答案列表")
    @PostMapping("answer/save")
    public BaseResponseDto getQuestAnswer(Answer answer) {
        questServiceImp.SaveAnswers(answer);
        return BaseResponseDto.success();
    }


}
