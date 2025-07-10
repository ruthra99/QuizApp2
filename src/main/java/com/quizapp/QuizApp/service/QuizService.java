package com.quizapp.QuizApp.service;

import com.quizapp.QuizApp.DAO.QuestionDAO;
import com.quizapp.QuizApp.DAO.QuizDAO;
import com.quizapp.QuizApp.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private static final Logger log = LoggerFactory.getLogger(QuizService.class);
    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List <Question> getQuestions= questionDAO.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(getQuestions);
        quizDAO.save(quiz);
        log.debug("9865058968"+quiz.toString());
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionById(int id) {
        Optional<Quiz> qResultSet=quizDAO.findById(id);
        List<Question> getQuestionsFromDB=qResultSet.get().getQuestions();
        List<QuestionWrapper> returnQuestionsToUser=new ArrayList<>();

        for(Question q:getQuestionsFromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            returnQuestionsToUser.add(qw);
        }

        return new ResponseEntity<>(returnQuestionsToUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<userResponse> userResponseList) {
        Quiz quiz=quizDAO.findById(id).get();
        List<Question> questions=quiz.getQuestions();

        int right=0;
        int i=0;

        for(userResponse forEveryResponse:userResponseList){
            if(forEveryResponse.getResponse().equals(questions.get(i).getRightAnswer())){
                    right++;
                    i++;
            }
        }
        return  new ResponseEntity<>(right,HttpStatus.OK);
    }

    public ResponseEntity<List<QuizSummary>> getAllQuizSummaries() {
        List<Quiz> quizzes = quizDAO.findAll();
        List<QuizSummary> summaries = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            summaries.add(new QuizSummary(quiz.getId(), quiz.getTitle()));
        }

        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

}
