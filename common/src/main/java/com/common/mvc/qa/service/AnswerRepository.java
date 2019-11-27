package com.common.mvc.qa.service;


import com.common.mvc.qa.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnswerRepository extends MongoRepository<Answer, Long> {

    Page<Answer> findAllByQuestId(String questId, Pageable pageable);

    Optional<Answer> findById(Long id);
}
