package com.codedifferently.groupone.SpyGlass.controllers;
import com.codedifferently.groupone.SpyGlass.entities.Contribution;
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

    //GET ALL GOALS
    @GetMapping
    public List<Goal> getGoals() {
        return goalService.getGoalsByUser();
    }

    //GET GOAL BY ID
    @GetMapping("/{id}")
    public Goal getGoalById(@PathVariable Long id) {
        Goal goal = goalService.getGoalById(id).orElseThrow(() -> new IllegalStateException("goal not found"));
        return goal;
    }

    //EVERY GOAL HAS TO BE MAPPED TO A USER!
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

    @PostMapping("/contribute/{id}")
    public ResponseEntity addContribution(@PathVariable Long id, @RequestBody Contribution contribution){
        return goalService.addContribution(id, contribution);
    }

}
