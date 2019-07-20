package com.article.admin.mvc.user.service;


import com.article.admin.mvc.user.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, Integer> {

    public Member findByUserId(String id);

}
