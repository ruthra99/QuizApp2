package com.quizapp.QuizApp.controller;

import com.quizapp.QuizApp.model.CreateQuizRequest;
import com.quizapp.QuizApp.model.QuestionWrapper;
import com.quizapp.QuizApp.model.QuizSummary;
import com.quizapp.QuizApp.model.userResponse;
import com.quizapp.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody CreateQuizRequest request){

        return quizService.createQuiz(request.getCategory(), request.getNumQ(), request.getTitle());
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionById(@PathVariable int id){
        return quizService.getQuestionById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz (@PathVariable Integer id,@RequestBody List<userResponse> userResponseList){
            return quizService.submitQuiz(id,userResponseList);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizSummary>> getAllQuizzes() {
        return quizService.getAllQuizSummaries();
    }


}
