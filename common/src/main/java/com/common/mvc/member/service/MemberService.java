package com.common.mvc.member.service;

import com.common.mvc.member.entity.Member;
import com.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member save(Member user) {
        user.setUserId(String.valueOf(IdWorker.getInstance().nextId()));
        return memberRepository.save(user);
    }


    public Member getMemberById(String id) {
        return memberRepository.findByUserId(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }


    /**
     * 插入新的会员信息
     */
    public Member insert(Member member) {
        try {
            return memberRepository.insert(member);
        } catch (Exception e) {
            return null;
        }
    }


    public Page<Member> findAllByJop(String jop, PageRequest pageRequest) {
        return memberRepository.findAllByJob(jop, pageRequest);
    }


    public Page<Member> findAll(PageRequest pageRequest) {
        return memberRepository.findAll(pageRequest);
    }

}
