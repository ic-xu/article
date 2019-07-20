package com.article.service.mvc.member.service;

import com.article.service.mvc.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class MemberService {

    private MemberRepository memberRepository;
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 插入新的会员信息
     *
     */
    public Member insert(Member member) {
        try {
            return memberRepository.insert(member);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 修改会员信息
     *
     */
    public Member save(Member member) {
        return memberRepository.save(member);
    }


    public Page<Member> findAllByJop(String jop, PageRequest pageRequest){
       return memberRepository.findAllByJob(jop,pageRequest);
    }


    public Page<Member> findAll( PageRequest pageRequest){
        return memberRepository.findAll(pageRequest);
    }


    public Member getMemberById(String userId){
        return memberRepository.findByUserId(userId);
    }
}
