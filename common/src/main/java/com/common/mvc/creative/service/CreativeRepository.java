package com.common.mvc.creative.service;


import com.common.mvc.creative.entity.Creative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreativeRepository extends MongoRepository<Creative, Long> {

    Page<Creative> findAllByStatus(int status, PageRequest pageable);

    Page<Creative> findAllByStatusAndClassify(int status,String classify, PageRequest pageable);
}
