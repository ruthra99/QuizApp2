package com.quizapp.QuizApp.model;



public class QuizSummary {
    private Integer id;
    private String title;

    public QuizSummary(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}

