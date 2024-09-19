package com.vns.quizservice.DAOS;


import com.vns.quizservice.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDAO extends JpaRepository<Quiz,Integer> {
}
