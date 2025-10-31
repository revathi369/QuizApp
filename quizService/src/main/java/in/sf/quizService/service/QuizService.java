package in.sf.quizService.service;

import in.sf.quizService.dao.QuizDao;
import in.sf.quizService.feign.QuizInterface;
import in.sf.quizService.model.QuestionWrapper;
import in.sf.quizService.model.Quiz;
import in.sf.quizService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        //RestTemplate is a class
        //call the generate url of question-service using RestTemplate http://localhost:8080/question/generate

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Quiz quiz= quizDao.findById(id).get();
       List<Integer> questionIds = quiz.getQuestionIds();
       quizInterface.getQuestionsFromId(questionIds);
       ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;

    }

   public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
       return  score;
   }
}
