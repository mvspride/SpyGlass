package com.codedifferently.groupone.SpyGlass.services;

import com.codedifferently.groupone.SpyGlass.entities.Goal;
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

    public List<Goal> getGoals() {
        return goalRepo.findAll();
    }

    public Goal getGoalById(@PathVariable Long id) {
        return goalRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    public ResponseEntity addGoal(@RequestBody Goal goal) throws URISyntaxException {
        Goal newGoal = goalRepo.save(goal);
        return ResponseEntity.created(new URI("/goals" + newGoal.getId())).body(newGoal);
    }

    public ResponseEntity editGoal(@PathVariable Long id, @RequestBody Goal goal) {
        Goal editingGoal = goalRepo.findById(id).orElseThrow(RuntimeException::new);
        editingGoal.setGoalAmount(goal.getGoalAmount());
        editingGoal.setContributionAmount(goal.getContributionAmount());
        editingGoal.setDescription(goal.getDescription());
        editingGoal.setContributionFrequency(goal.getContributionFrequency());
        editingGoal.setPriority(goal.getPriority());
        editingGoal = goalRepo.save(goal);
        return ResponseEntity.ok(editingGoal);
    }

    public ResponseEntity deleteGoal(@PathVariable Long id) {
        goalRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }





}
