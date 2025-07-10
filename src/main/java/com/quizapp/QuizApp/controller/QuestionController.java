package com.quizapp.QuizApp.controller;


import com.quizapp.QuizApp.model.Question;
import com.quizapp.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@ControllerAdvice
@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

//        if (true) { // your condition here
//            throw new RuntimeException("Something went wrong manually!");
//        }
//        return ResponseEntity.ok("All good");

        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        System.out.println(category);
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable int id) {
        return questionService.deleteRecord(id);
    }
    @PutMapping("update")
    public ResponseEntity<String> updateRecord(@RequestBody Question question){
        return questionService.updateRecord(question);
    }
}
