package com.codedifferently.groupone.SpyGlass.services;

import com.codedifferently.groupone.SpyGlass.entities.Goal;
import com.codedifferently.groupone.SpyGlass.exceptions.goal.GoalNotFoundException;
import com.codedifferently.groupone.SpyGlass.repos.GoalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class GoalService {

    @Autowired
    private GoalRepo goalRepo;

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
    public Goal getGoalById(@PathVariable Long id) throws GoalNotFoundException {
        if (!goalRepo.existsById(id)) {
            throw new GoalNotFoundException(id);
        }
        return goalRepo.getById(id);
    }

    /**
     * creates new goal
     * @param goal
     * @throws URISyntaxException
     */
    public ResponseEntity addGoal(@RequestBody Goal goal) throws URISyntaxException {
        Goal newGoal = goalRepo.save(goal);
        return ResponseEntity.created(new URI("/goals" + newGoal.getId())).body(newGoal);
    }

    /**
     * edits goal
     * @param id
     * @param goal
     * @throws GoalNotFoundException
     */
    public ResponseEntity editGoal(@PathVariable Long id, @RequestBody Goal goal) throws GoalNotFoundException {
        if (!goalRepo.existsById(id)) {
            throw new GoalNotFoundException(id);
        }
        Goal editingGoal = goalRepo.findById(id).orElseThrow(RuntimeException::new);
        editingGoal.setGoalAmount(goal.getGoalAmount());
        editingGoal.setContributionAmount(goal.getContributionAmount());
        editingGoal.setDescription(goal.getDescription());
        editingGoal.setContributionFrequency(goal.getContributionFrequency());
        editingGoal.setPriority(goal.getPriority());
        editingGoal = goalRepo.save(goal);
        return ResponseEntity.ok(editingGoal);
    }

    /**
     * deletes goal
     * @param id
     * @throws GoalNotFoundException
     */
    public ResponseEntity deleteGoal(@PathVariable Long id) throws GoalNotFoundException {
        if (!goalRepo.existsById(id)) {
            throw new GoalNotFoundException(id);
        }
        goalRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
