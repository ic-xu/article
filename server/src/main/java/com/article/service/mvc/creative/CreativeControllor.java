package com.article.service.mvc.creative;

import com.common.mvc.creative.entity.Creative;
import com.common.mvc.creative.service.CreativeService;
import com.common.utils.BaseResponseDto;
import com.common.utils.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author ubuntu
 * @date On 19-11-30 下午2:57
 */
@RestController
@Api(tags = "创意相关")
@RequestMapping("creative/")
public class CreativeControllor {

    @Autowired
    private CreativeService creativeService;

    @PostMapping("save/creative")
    @ApiOperation("保存创意")
    public BaseResponseDto saveCreative(Creative creative) {
        creative.setId(IdWorker.getInstance().nextId());
        creative.setCreateTime(System.currentTimeMillis());
        creativeService.saveCreativeClassify(creative.getClassify());
        return BaseResponseDto.success(creativeService.saveCreative(creative));
    }

    @GetMapping("/{page}/json")
    @ApiOperation("获取创意信息")
    public BaseResponseDto findAll(@PathVariable("page") int page, @RequestParam(required = false) String classify) {
        //按照赞同的人数排序
        PageRequest pageRequest = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "agreeCount"));
        //1，表示审核通过
        Page<Creative> all = creativeService.findAllByStatus(1, classify, pageRequest);

        HashMap<String, Object> res = new HashMap<>();
        res.put("totalPages", all.getTotalPages());
        res.put("content", all.getContent());
        res.put("size", 20);
        res.put("number", all.getNumber());
        res.put("totalElement", all.getTotalElements());
        res.put("current", all.getPageable().getPageNumber());
        return BaseResponseDto.success(res);
    }

    @GetMapping("/classify/json")
    @ApiOperation("获取创意分类")
    public BaseResponseDto classifyJson() {
        return BaseResponseDto.success(creativeService.findAllClassify());
    }
}
