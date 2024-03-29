package com.codedifferently.groupone.SpyGlass.services;

import com.codedifferently.groupone.SpyGlass.entities.Contribution;
import com.codedifferently.groupone.SpyGlass.entities.Goal;
import com.codedifferently.groupone.SpyGlass.entities.User;
import com.codedifferently.groupone.SpyGlass.enums.Priority;
import com.codedifferently.groupone.SpyGlass.exceptions.goal.GoalNotFoundException;
import com.codedifferently.groupone.SpyGlass.repos.ContributionRepository;
import com.codedifferently.groupone.SpyGlass.repos.GoalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    @Autowired
    private GoalRepo goalRepo;

    @Autowired
    private ContributionRepository contributionRepo;



    /**
     * @return list of all goals
     */
    public List<Goal> getGoals() {
        return goalRepo.findAll();
    }

    /**
     * @param id
     * @return goal found by id
     * @throws GoalNotFoundException
     */
    public Optional<Goal> getGoalById(Long id) throws GoalNotFoundException {
        if (!goalRepo.existsById(id)) {
            throw new GoalNotFoundException(id);
        }
        return goalRepo.findById(id);
    }

    public List<Goal> getGoalsByUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User)auth.getPrincipal();
        return goalRepo.findAllByUser(currentUser);
    }

    /**
     * creates new goal
     * @param goal
     * @throws URISyntaxException
     */
    public ResponseEntity addGoal(Goal goal) throws URISyntaxException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User)auth.getPrincipal();
        goal.setUser(currentUser);
        Goal newGoal = goalRepo.save(goal);
        return ResponseEntity.created(new URI("/goals" + newGoal.getId())).body(newGoal);
    }

    /**
     * edits goal
     * @param goalId
     * @param goal
     * @throws GoalNotFoundException
     */
    public ResponseEntity editGoal(Long goalId,Goal goal) throws GoalNotFoundException {
        Goal currentGoal = goalRepo.findById(goalId).orElseThrow(()->new GoalNotFoundException(goalId));
        goalRepo.updateGoal(goal.getDescription(),goal.getDeadLine(),goal.getFrequency(),
               goal.getPriority(),goal.getContributionAmount(),
               goal.getGoalAmount(),goal.getCurrentlySaved(), goal.getPictureURL(), goalId);
        //goalRepo.save(goal);
        return ResponseEntity.ok(goal);
    }

    /**
     * deletes goal
     * @param id
     * @throws GoalNotFoundException
     */
    public ResponseEntity deleteGoal(Long id) throws GoalNotFoundException {
        if (!goalRepo.existsById(id)) {
            throw new GoalNotFoundException(id);
        }
        goalRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity addContribution(Long id, Contribution contribution){
        Goal goal = goalRepo.getById(id);
        contribution.setGoal(goal);
        contributionRepo.save(contribution);
        goal.addContribution(contribution);
        goalRepo.updateGoal(goal.getDescription(),goal.getDeadLine(),goal.getFrequency(),
                goal.getPriority(),goal.getContributionAmount(),
                goal.getGoalAmount(),goal.getCurrentlySaved() + contribution.getAmount(), goal.getPictureURL(), id);
        return ResponseEntity.ok(contribution);
    }


}
