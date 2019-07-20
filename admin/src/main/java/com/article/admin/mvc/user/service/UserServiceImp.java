package com.article.admin.mvc.user.service;

import com.article.admin.mvc.user.entity.Member;
import com.article.admin.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImp {

    @Autowired
    private MemberRepository memberRepository;


    public Member save(Member user) {
        user.setUserId(String.valueOf(new IdWorker().nextId()));
        return memberRepository.save(user);
    }


    public Member getMemberById(String id){
        return memberRepository.findByUserId(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
