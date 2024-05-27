package com.techrs.avante_rs.repositories;

import com.techrs.avante_rs.domain.Answer;
import com.techrs.avante_rs.domain.TypeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Optional<Answer> findByIdAndTypeQuestion(Long id, TypeQuestion typeQuestion);
}
