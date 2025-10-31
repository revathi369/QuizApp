package in.sf.question_service.controller;

import in.sf.question_service.model.Question;
import in.sf.question_service.model.QuestionWrapper;
import in.sf.question_service.model.Response;
import in.sf.question_service.service.QuestionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        //return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("category") String category){
        return questionService.getQuestionsByCategory(category);
    }
    @GetMapping("difficulty/{difficulty}")
    public ResponseEntity<List<Question>> getQuestionsByDifficultylevel(@PathVariable("difficulty") String difficulty){
        return questionService.getQuestionsByDifficultylevel(difficulty);
    }
    //    {
//
//            "questionTitle": "Which Java keyword is used to create a subclass?",
//            "option1": "class",
//            "option2": "interface",
//            "option3": "extends",
//            "option4": "implements",
//            "rightAnswer": "extends",
//            "difficultylevel": "Easy",
//            "category": "JAVA"
//    }
    //example format to input the data
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    //generate questions for a quiz in the form question ids
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam String category, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(category, numQuestions);

    }

    //give the questions based on the quiz id

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromId(questionIds);
    }
    //get score

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
