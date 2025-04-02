package com.example.CodeJudge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long problemDescriptionId;
    @NotBlank
    private String problemStatement;
    @NotBlank
    private String sampleInput;
    @NotBlank
    private String sampleOutput;
    @NotBlank
    private String constraints;

    @OneToOne
    @JoinColumn(name = "problemId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Problem problem;
}
