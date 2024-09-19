package com.vns.quizservice.models;

import lombok.Data;

@Data
public class QuizDTO {
    String categoryName;
    Integer numQuestions;
    String title;
}