package com.example.CodeJudge.service;

import com.example.CodeJudge.execptions.APIException;
import com.example.CodeJudge.execptions.ResourceNotFoundException;
import com.example.CodeJudge.model.Category;
import com.example.CodeJudge.model.Favourite;
import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.User;
import com.example.CodeJudge.modelDTOs.FavouriteDTO;
import com.example.CodeJudge.modelDTOs.ProblemDTO;
import com.example.CodeJudge.modelDTOs.ProblemResponce;
import com.example.CodeJudge.repositories.FavouriteRepository;
import com.example.CodeJudge.repositories.ProblemRepository;
import com.example.CodeJudge.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import com.example.CodeJudge.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService{
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ProblemDTO addProblem(Long categoryId, ProblemDTO problemDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));

        boolean isProblemNotPresent = true;

        List<Problem> problems = category.getProblems();
        for (Problem value : problems) {
            if (value.getProblemName().equals(problemDTO.getProblemName())) {
                isProblemNotPresent = false;
                break;
            }
        }

        if (isProblemNotPresent) {
            Problem problem = modelMapper.map(problemDTO, Problem.class);
            problem.setCategory(category);
            Problem savedProblem = problemRepository.save(problem);
            return modelMapper.map(savedProblem, ProblemDTO.class);
        } else {
            throw new APIException("Product already exist!!");
        }

    }
    @Override
    public ProblemResponce getProblems(Long categoryId, String difficulty, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        if (difficulty != null && !Arrays.asList("EASY", "MEDIUM", "HARD").contains(difficulty.toUpperCase())) {
            throw new InvalidParameterException("Difficulty must be EASY, MEDIUM or HARD");
        }

        Category category = null;
        if (categoryId != null) {
            category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        }

        // Handle sorting
        List<String> allowedSortFields = Arrays.asList("problemId", "problemName", "difficulty");

        if (sortBy == null || !allowedSortFields.contains(sortBy)) {
            sortBy = "problemId"; // default sort field
        }

        if (sortOrder == null || (!sortOrder.equalsIgnoreCase("asc") && !sortOrder.equalsIgnoreCase("desc"))) {
            sortOrder = "asc"; // default sort order
        }

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Problem> problems;

        System.out.println("Total count in DB: " + problemRepository.count());


        if (category != null && difficulty != null) {
            problems = problemRepository.findByCategoryAndDifficultyIgnoreCase(category, difficulty, pageable);
        } else if (category != null) {
            problems = problemRepository.findByCategory(category, pageable);
        } else if (difficulty != null) {
            problems = problemRepository.findByDifficultyIgnoreCase(difficulty, pageable);
        } else {
            problems = problemRepository.findAll(pageable);
        }

        List<Problem> problemList = problems.getContent();
        List<ProblemDTO> problemDTOS = problemList.stream()
                .map(problem -> new ProblemDTO(
                        problem.getProblemId(),
                        problem.getProblemName(),
                        problem.getDifficulty(),
                        problem.getCategory()
                ))
                .toList();

        System.out.println("Category: " + category);
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Fetching problems with: " +
                (category != null ? "categoryId=" + category.getCategoryId() : "no category") + ", " +
                (difficulty != null ? "difficulty=" + difficulty : "no difficulty")
        );
        System.out.println("Fetched problem count: " + problemList.size());


        ProblemResponce problemResponce = new ProblemResponce();
        problemResponce.setProblemDTOS(problemDTOS);
        problemResponce.setPageNumber(problems.getNumber());
        problemResponce.setPageSize(problems.getSize());
        problemResponce.setTotalElements(problems.getTotalElements());
        problemResponce.setTotalPages(problems.getTotalPages());
        problemResponce.setLastPage(problems.isLast());

        return problemResponce;
    }



    @Override
    @Transactional(readOnly = true)
    public ProblemResponce getAllUserFavourites(Long userId,Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","userId",userId));
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Problem> problemPage = favouriteRepository.findProblemsByUser(user,pageDetails);
        List<Problem> problems = problemPage.getContent();
        List<ProblemDTO> problemDTOS = problems.stream().map(problem->modelMapper.map(problem, ProblemDTO.class)).toList();
        ProblemResponce problemResponce = new ProblemResponce();
        problemResponce.setProblemDTOS(problemDTOS);
        problemResponce.setTotalElements(problemPage.getTotalElements());
        problemResponce.setTotalPages(problemPage.getTotalPages());
        problemResponce.setPageNumber(problemPage.getNumber());
        problemResponce.setPageSize(problemPage.getSize());
        problemResponce.setLastPage(problemPage.isLast());

        return problemResponce;
    }

    @Override
    @Transactional
    public boolean toggleFavorite(Long userId, Long problemId) {

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "id", userId);
        }
        if (!problemRepository.existsById(problemId)) {
            throw new ResourceNotFoundException("Problem", "id", problemId);
        }

        User user = userRepository.getReferenceById(userId);
        Problem problem = problemRepository.getReferenceById(problemId);

        // 2. Atomic toggle operation
        Optional<Favourite> existing = favouriteRepository.findByUserAndProblem(user, problem);
        if (existing.isPresent()) {
            favouriteRepository.delete(existing.get());
            return false;
        } else {
           Favourite favourite = new Favourite();
           favourite.setUser(user);
           favourite.setProblem(problem);
           favouriteRepository.save(favourite);
            return true;
        }
    }


    @Override
    public ProblemResponce getAllProblems(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Problem> pageProblems = problemRepository.findAll(pageDetails);

        List<Problem> problems = pageProblems.getContent();

        List<ProblemDTO> problemDTOS = problems.stream()
                .map(problem -> modelMapper.map(problem, ProblemDTO.class))
                .toList();

        ProblemResponce problemResponce = new ProblemResponce();
        problemResponce.setProblemDTOS(problemDTOS);
        problemResponce.setPageNumber(pageProblems.getNumber());
        problemResponce.setPageSize(pageProblems.getSize());
        problemResponce.setTotalElements(pageProblems.getTotalElements());
        problemResponce.setTotalPages(pageProblems.getTotalPages());
        problemResponce.setLastPage(pageProblems.isLast());
        return problemResponce;

    }

    @Override
    public ProblemResponce searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Problem> pageProblems = problemRepository.findByCategoryOrderByDifficultyAsc(category, pageDetails);

        List<Problem> problems = pageProblems.getContent();

        if(problems.isEmpty()){
            throw new APIException(category.getCategoryName() + " category does not have any products");
        }

        List<ProblemDTO> problemDTOS = problems.stream()
                .map(problem -> modelMapper.map(problem, ProblemDTO.class))
                .toList();

        ProblemResponce problemResponce = new ProblemResponce();
        problemResponce.setProblemDTOS(problemDTOS);
        problemResponce.setPageSize(pageProblems.getSize());
        problemResponce.setTotalElements(pageProblems.getTotalElements());
        problemResponce.setTotalPages(pageProblems.getTotalPages());
        problemResponce.setLastPage(pageProblems.isLast());
        return problemResponce;

    }

    @Override
    public ProblemResponce searchProblemByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Problem> pageProblems = problemRepository.findByProblemNameLikeIgnoreCase('%' + keyword + '%', pageDetails);

        List<Problem> problems = pageProblems.getContent();
        List<ProblemDTO> problemDTOS = problems.stream()
                .map(problem -> modelMapper.map(problem, ProblemDTO.class))
                .toList();

        if(problems.isEmpty()){
            throw new APIException("Products not found with keyword: " + keyword);
        }

        ProblemResponce problemResponce = new ProblemResponce();
        problemResponce.setProblemDTOS(problemDTOS);
        problemResponce.setPageSize(pageProblems.getSize());
        problemResponce.setTotalElements(pageProblems.getTotalElements());
        problemResponce.setTotalPages(pageProblems.getTotalPages());
        problemResponce.setLastPage(pageProblems.isLast());
        return problemResponce;

    }

    @Override
    public ProblemDTO updateProblem(Long problemId, ProblemDTO problemDTO) {
        Problem problemFromDb = problemRepository.findById(problemId)
                .orElseThrow(() -> new ResourceNotFoundException("Problem", "problemId", problemId));

        Problem problem = modelMapper.map(problemDTO, Problem.class);

        problemFromDb.setProblemName(problem.getProblemName());
        problemFromDb.setCategory(problem.getCategory());
        problemFromDb.setDifficulty(problem.getDifficulty());



        Problem savedProblem = problemRepository.save(problemFromDb);

        return modelMapper.map(savedProblem, ProblemDTO.class);

    }

    @Override
    public ProblemDTO deleteProblem(Long problemId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ResourceNotFoundException("Problem", "problemId", problemId));

        problemRepository.delete(problem);
        return modelMapper.map(problem, ProblemDTO.class);
    }




}
