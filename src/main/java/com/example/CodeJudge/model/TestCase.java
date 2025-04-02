package com.example.CodeJudge.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Input cannot be blank")
    private String input;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Expected output cannot be blank")
    private String expectedOutput;

    @Column(nullable = false)
    private boolean isSample = false; // Marks sample test cases

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;
}
