package in.sf.quizService.model;

import lombok.Data;

@Data
//Data Transfer Object
public class QuizDto {
    String categoryName;
    Integer numQuestions;
    String title;
}
