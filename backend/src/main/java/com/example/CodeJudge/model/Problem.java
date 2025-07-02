package com.example.CodeJudge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long problemId;
    @NotBlank
    private String problemName;
    @NotBlank
    @Size(min = 4, message = "problem difficulty name must contain atleast 4 characters")
    private String difficulty;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;


    @OneToOne(mappedBy = "problem",cascade = CascadeType.ALL,orphanRemoval = true )
    @ToString.Exclude
    private ProblemDescription problemDescription;

    @OneToMany(mappedBy = "problem",cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<TestCase> testCase;

    @OneToMany(mappedBy = "problem", orphanRemoval = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @ToString.Exclude
    private List<Submission> submissions;


}
