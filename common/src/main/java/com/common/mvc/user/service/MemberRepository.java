package com.common.mvc.user.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.common.mvc.user.entity.Member;

public interface MemberRepository extends MongoRepository<Member, String> {

    Member findByUsername(String userName);

    Page<Member> findAllByJob(String jop, PageRequest pageRequest);

}
