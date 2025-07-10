package com.quizapp.QuizApp.model;

public class CreateQuizRequest {

    private String category;
    private int numQ;
    private String title;

    public CreateQuizRequest() {} // 🔴 REQUIRED for JSON deserialization

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumQ() {
        return numQ;
    }

    public void setNumQ(int numQ) {
        this.numQ = numQ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
