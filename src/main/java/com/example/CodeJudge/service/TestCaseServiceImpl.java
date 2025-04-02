package com.example.CodeJudge.service;

import com.example.CodeJudge.execptions.APIException;
import com.example.CodeJudge.execptions.ResourceNotFoundException;
import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.TestCase;
import com.example.CodeJudge.modelDTOs.TestCaseDTO;
import com.example.CodeJudge.modelDTOs.TestCaseResponce;
import com.example.CodeJudge.repositories.ProblemRepository;
import com.example.CodeJudge.repositories.TestCaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestCaseServiceImpl implements TestCaseService{
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TestCaseRepository testCaseRepository;
    @Override
    public TestCaseDTO addTestCase(TestCaseDTO testCaseDTO, Long problemId) {

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ResourceNotFoundException("Problem", "problemId", problemId));


        boolean exists = testCaseRepository.existsByInputAndExpectedOutputAndProblem(
                testCaseDTO.getInput(),
                testCaseDTO.getExpectedOutput(),
                problem
        );

        if (exists) {
            throw new APIException("Test case with these inputs and outputs already exists for this problem");
        }


        TestCase testCase = modelMapper.map(testCaseDTO, TestCase.class);
        testCase.setProblem(problem);


        TestCase savedTestCase = testCaseRepository.save(testCase);
        return modelMapper.map(savedTestCase, TestCaseDTO.class);
    }

    @Override
    public TestCaseResponce getAllTestCases(Long problemId) {
        Problem problem = problemRepository.findById(problemId).orElseThrow(()-> new ResourceNotFoundException("Problem","problemId",problemId));
        List<TestCase> testCases = testCaseRepository.findByProblem(problem);

        List<TestCaseDTO> testCaseDTOS = testCases.stream().map(testCase -> modelMapper.map(testCase, TestCaseDTO.class)).toList();

        return new TestCaseResponce(testCaseDTOS,testCaseDTOS.size());
    }

    @Override
    @Transactional
    public TestCaseDTO updateTestCase(TestCaseDTO testCaseDTO, Long testCaseId) {

        TestCase existing = testCaseRepository.findById(testCaseId)
                .orElseThrow(() -> new ResourceNotFoundException("TestCase", "id", testCaseId));

        updateProblemIfChanged(existing, testCaseDTO);

        validateDuplicateTestCase(existing, testCaseDTO);

        updateTestCaseFields(existing, testCaseDTO);

        TestCase updated = testCaseRepository.save(existing);
        return modelMapper.map(updated, TestCaseDTO.class);
    }

    @Override
    public TestCaseDTO deleteTestCase(Long testCaseId) {

        TestCase testCase = testCaseRepository.findById(testCaseId).orElseThrow(()->new ResourceNotFoundException("TestCase","testCaseId",testCaseId));
         testCaseRepository.delete(testCase);
         return modelMapper.map(testCase,TestCaseDTO.class);
    }


    private void updateProblemIfChanged(TestCase existing, TestCaseDTO testCaseDTO) {
        if (testCaseDTO.getProblemId() != null &&
                !existing.getProblem().getProblemId().equals(testCaseDTO.getProblemId())) {
            Problem newProblem = problemRepository.findById(testCaseDTO.getProblemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Problem", "id", testCaseDTO.getProblemId()));
            existing.setProblem(newProblem);
        }
    }


    private void validateDuplicateTestCase(TestCase existing, TestCaseDTO testCaseDTO) {
        boolean inputChanged = testCaseDTO.getInput() != null && !testCaseDTO.getInput().equals(existing.getInput());
        boolean outputChanged = testCaseDTO.getExpectedOutput() != null && !testCaseDTO.getExpectedOutput().equals(existing.getExpectedOutput());

        if (inputChanged || outputChanged) {
            boolean duplicateExists = testCaseRepository.existsByInputAndExpectedOutputAndProblem(
                    inputChanged ? testCaseDTO.getInput() : existing.getInput(),
                    outputChanged ? testCaseDTO.getExpectedOutput() : existing.getExpectedOutput(),
                    existing.getProblem()
            );

            if (duplicateExists) {
                throw new APIException("Test case with these inputs/outputs already exists for this problem");
            }
        }
    }


    private void updateTestCaseFields(TestCase existing, TestCaseDTO testCaseDTO) {
        if (testCaseDTO.getInput() != null) {
            existing.setInput(testCaseDTO.getInput());
        }
        if (testCaseDTO.getExpectedOutput() != null) {
            existing.setExpectedOutput(testCaseDTO.getExpectedOutput());
        }


    }





}
