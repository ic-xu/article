package com.article.service.mvc.member.controllor;

import com.article.service.mvc.member.entity.Member;
import com.article.service.mvc.member.entity.MemberData;
import com.article.service.mvc.member.service.MemberService;
import com.article.service.utils.BaseResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "用户相关")
@RequestMapping("member")
public class MemberControllor {



    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 新增会员
     *
     * @param member er
     * @return BaseResponseDto
     */
    @ApiOperation("新增会员")
    @PostMapping("/insert")
    public BaseResponseDto insert(Member member) {
        Member insert = memberService.insert(member);
        if (null == insert)
            return BaseResponseDto.error(400, "用户已经存在");
        else return BaseResponseDto.success(insert);
    }


    /**
     * 新增会员
     *
     * @param member member
     * @return BaseResponseDto
     */
    @ApiOperation("update member info")
    @PostMapping("/update")
    public BaseResponseDto update(Member member) {
        Member insert = memberService.save(member);
        if (null == insert)
            return BaseResponseDto.error(400, "用户已经存在");
        else return BaseResponseDto.success(insert);
    }

    /**
     * 新增会员
     *
 @return BaseResponseDto  * @return BaseResponseDto
     */
    @ApiOperation("findAllByJop")
    @GetMapping("/findAllByJop")
    public BaseResponseDto findAllByJop(@RequestParam(defaultValue = "0") int pageNumber) {
        String jop = "111";
        PageRequest pageRequest = PageRequest.of(pageNumber, 20,
                Sort.by(new Sort.Order(Sort.Direction.DESC, "createTime")));
        Page<Member> allByJop = memberService.findAll(pageRequest);

        MemberData memberData = new MemberData();
        memberData.setJop(jop);
        memberData.setMembersData(allByJop.getContent());
        List<MemberData> memberDatas = new ArrayList<>();
        memberDatas.add(memberData);
        return BaseResponseDto.success(memberDatas);
    }

}
