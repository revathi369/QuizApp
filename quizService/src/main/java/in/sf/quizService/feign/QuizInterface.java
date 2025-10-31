package in.sf.quizService.feign;

import in.sf.quizService.model.QuestionWrapper;
import in.sf.quizService.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//which client we're connecting to this project we've to mention here
@FeignClient("question-service")
public interface QuizInterface {

    //method's definitions we want to call from the other service to work with this service
    //generate questions for a quiz in the form question ids
    //in the url we've to mention question service also, to call this method from that service
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam String category, @RequestParam Integer numQuestions);

    //give the questions based on the quiz id
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    //get score
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
