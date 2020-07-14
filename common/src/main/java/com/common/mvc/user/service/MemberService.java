package com.common.mvc.user.service;

import com.common.mvc.user.entity.Member;
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
        Member username = memberRepository.findByUsername(user.getUsername());
        if(null == username){
            user.setId("M"+IdWorker.getInstance().nextId());
            return memberRepository.save(user);
        }
        return username;
    }


    public Member getMemberById(String username) {
        return memberRepository.findByUsername(username);
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
