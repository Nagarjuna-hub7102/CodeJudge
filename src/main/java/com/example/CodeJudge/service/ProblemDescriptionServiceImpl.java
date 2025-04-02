package com.example.CodeJudge.service;

import com.example.CodeJudge.execptions.ResourceNotFoundException;
import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.ProblemDescription;
import com.example.CodeJudge.modelDTOs.ProblemDescriptionDTO;
import com.example.CodeJudge.repositories.ProblemDescriptionRepository;
import com.example.CodeJudge.repositories.ProblemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemDescriptionServiceImpl implements ProblemDescriptionService{
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ProblemDescriptionRepository problemDescriptionRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProblemDescriptionDTO getDescriptionByProblemId(Long problemId) {
        Problem problem = problemRepository.findById(problemId).orElseThrow(()-> new ResourceNotFoundException("Problem","ProblemId",problemId));
        ProblemDescription problemDescription = problemDescriptionRepository.findByProblem(problem)
                .orElseThrow(() -> new ResourceNotFoundException("ProblemDescription", "Problem", problemId));


        ProblemDescriptionDTO problemDescriptionDTO = modelMapper.map(problemDescription,ProblemDescriptionDTO.class);
        problemDescriptionDTO.setProblemName((problem.getProblemName()));
        return problemDescriptionDTO;
    }

    @Override
    public ProblemDescriptionDTO updateDescription(ProblemDescriptionDTO problemDescriptionDTO, Long problemDescriptionId) {
        ProblemDescription problemDescriptionFromDb = problemDescriptionRepository.findById(problemDescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("problemDescription", "problemDescriptionId", problemDescriptionId));


        Problem problem = problemRepository.findByProblemName(problemDescriptionDTO.getProblemName())
                .orElseThrow(() -> new ResourceNotFoundException("Problem", "problemName", problemDescriptionDTO.getProblemName()));


        problemDescriptionFromDb.setProblemStatement(problemDescriptionDTO.getProblemStatement());
        problemDescriptionFromDb.setConstraints(problemDescriptionDTO.getConstraints());
        problemDescriptionFromDb.setSampleInput(problemDescriptionDTO.getSampleInput());
        problemDescriptionFromDb.setSampleOutput(problemDescriptionDTO.getSampleOutput());
        problemDescriptionFromDb.setProblem(problem);


        ProblemDescription updatedProblemDescription = problemDescriptionRepository.save(problemDescriptionFromDb);
        return modelMapper.map(updatedProblemDescription, ProblemDescriptionDTO.class);

    }


    @Override
    public void deleteProblemDescription(Long problemDescriptionId) {
        ProblemDescription problemDescription = problemDescriptionRepository.findById(problemDescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ProblemDescription", "problemDescriptionId", problemDescriptionId));

        problemDescriptionRepository.delete(problemDescription);
    }


}
