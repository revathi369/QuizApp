package in.sf.quizService.controller;

import in.sf.quizService.model.QuestionWrapper;
import in.sf.quizService.model.QuizDto;
import in.sf.quizService.model.Response;
import in.sf.quizService.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    //http://localhost:8080/quiz/create?category=Java&numQ=5&title=JQuiz --- example url
    //Automatically creates by using Many to Many Mapping
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }


    //http://localhost:8080/quiz/get/1 ---- example url
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        //Creating a wrapper where it has all the questions, options except the answer of it.
        return quizService.getQuizQuestions(id);
    }


    //http://localhost:8080/quiz/submit/1
    //We've to submit the quiz answers in json format
    //eg: here we got questions 7, 10, 8 , 6 , 4 -- so first quiz is created, then we've to give the answers to submit, where at id: 1 this quiz is created.
    /*[
    { "id" : 7, "response" : "do-while loop"},
    {"id" : 10, "response" : "ArrayList"},
    {"id" : 8, "response" : "To terminate a loop or switch statement and transfer control to the next statement."},
    {"id" : 6, "response" : "final int x = 5;"},
    {"id" : 4, "response" : "throw"}
    ]*/

    //the input format to submit the answer
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
