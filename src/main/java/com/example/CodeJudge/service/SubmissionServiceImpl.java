package com.example.CodeJudge.service;

import com.example.CodeJudge.execptions.ResourceNotFoundException;
import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.Submission;
import com.example.CodeJudge.model.User;
import com.example.CodeJudge.modelDTOs.SubmissionDTO;
import com.example.CodeJudge.modelDTOs.SubmissionResponce;
import com.example.CodeJudge.repositories.ProblemRepository;
import com.example.CodeJudge.repositories.SubmissionRepository;
import com.example.CodeJudge.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubmissionResponce getAllSubmissions(
            Long userId,
            Long problemId,
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortOrder) {


        User user = validateUser(userId);
        Problem problem = validateProblem(problemId);

        Pageable pageable = createPageable(pageNumber, pageSize, sortBy, sortOrder);

        Page<Submission> submissionPage = getFilteredSubmissions(user, problem, pageable);

        return makeResponse(submissionPage);
    }

    // Helper Methods
    private User validateUser(Long userId) {
        if (userId == null) return null;
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    private Problem validateProblem(Long problemId) {
        if (problemId == null) return null;
        return problemRepository.findById(problemId)
                .orElseThrow(() -> new ResourceNotFoundException("Problem", "id", problemId));
    }

    private Pageable createPageable(int page, int size, String sortBy, String sortDir) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        return PageRequest.of(page, size, Sort.by(direction, sortBy));
    }

    private Page<Submission> getFilteredSubmissions(User user, Problem problem, Pageable pageable) {
        if (problem != null && user != null) {
            return submissionRepository.findByUserAndProblem(user, problem, pageable);
        } else if (problem != null) {
            return submissionRepository.findByProblem(problem, pageable);
        } else if (user != null) {
            return submissionRepository.findByUser(user, pageable);
        } else {
            return submissionRepository.findAll(pageable);
        }
    }

    private SubmissionResponce makeResponse(Page<Submission> page) {
        List<SubmissionDTO> dtos = page.getContent().stream()
                .map(sub -> modelMapper.map(sub, SubmissionDTO.class))
                .toList();

        return new SubmissionResponce(dtos, page.getNumber(), page.getSize(), page.getTotalElements(), page.getNumberOfElements(), page.isLast());

    }
}
