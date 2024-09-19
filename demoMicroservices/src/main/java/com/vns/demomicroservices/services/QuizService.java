package com.vns.demomicroservices.services;

import com.vns.demomicroservices.DAOS.QuestionDAO;
import com.vns.demomicroservices.DAOS.QuizDAO;
import com.vns.demomicroservices.models.Question;
import com.vns.demomicroservices.models.QuestionWrapper;
import com.vns.demomicroservices.models.Quiz;
import com.vns.demomicroservices.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuestionDAO questionDAO;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDAO.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        while (i<responses.size()) {
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))   right++;
        }
        i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}