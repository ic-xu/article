package com.article.service.mvc.member.service;

import com.article.service.mvc.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member,String> {

    Page<Member> findAllByJob(String jop, PageRequest pageRequest);

    Member findByUserId(String userid);
}
