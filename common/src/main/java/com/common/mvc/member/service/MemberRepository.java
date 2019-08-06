package com.common.mvc.member.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.member.entity.Member;

public interface MemberRepository extends MongoRepository<Member, Integer> {

    public Member findByUserId(String id);

    Page<Member> findAllByJob(String jop, PageRequest pageRequest);

}
