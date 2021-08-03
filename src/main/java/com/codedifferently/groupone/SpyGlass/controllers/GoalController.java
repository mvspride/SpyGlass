package com.codedifferently.groupone.SpyGlass.controllers;

import com.codedifferently.groupone.SpyGlass.entities.Goal;
import com.codedifferently.groupone.SpyGlass.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @GetMapping
    public List<Goal> getGoals() {
        return goalService.getGoals();
    }

    @GetMapping("/{id}")
    public Goal getGoalById(@PathVariable Long id) {
        return goalService.getGoalById(id);
    }

    @PostMapping
    public ResponseEntity addToDo(@RequestBody Goal goal) throws URISyntaxException {
        return goalService.addGoal(goal);
    }

    @PutMapping("/{id}")
    public ResponseEntity editToDo(@PathVariable Long id, @RequestBody Goal goal) {
        return goalService.editGoal(id, goal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteToDo(@PathVariable Long id) {
        return goalService.deleteGoal(id);
    }

}
