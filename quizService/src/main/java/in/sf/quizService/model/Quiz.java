package in.sf.quizService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ElementCollection //we use this bcoz we're using integers not objects.If they're objects we'll use ManyToMany Mapping
    private List<Integer> questionIds;
}
