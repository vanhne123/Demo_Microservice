package com.vns.demomicroservices.DAOS;

import com.vns.demomicroservices.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDAO extends JpaRepository<Quiz,Integer> {
}
