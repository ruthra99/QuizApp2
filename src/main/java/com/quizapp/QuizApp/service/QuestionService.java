package com.quizapp.QuizApp.service;

import com.quizapp.QuizApp.DAO.QuestionDAO;
import com.quizapp.QuizApp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {
    @Autowired
    QuestionDAO dao2;

    public  ResponseEntity<String> addQuestion(Question question) {
        dao2.save(question);

        try {
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(dao2.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(dao2.findBycategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);



    }
    //copy
    public ResponseEntity<String> deleteRecord(int id) {
        dao2.deleteById(id);
        try {
            return new ResponseEntity<>(
                    "Record Deleted", HttpStatus.CREATED);
        }catch(Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("Record Not Deleted", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateRecord(Question question) {
        dao2.save(question);
        try {
            return new ResponseEntity<>(
                    "Record Updated", HttpStatus.CREATED);
        }catch(Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("Record Not Updated", HttpStatus.BAD_REQUEST);
    }
}
