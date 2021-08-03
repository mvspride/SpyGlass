package com.codedifferently.groupone.SpyGlass.controllers;

import com.codedifferently.groupone.SpyGlass.entities.Goal;
import com.codedifferently.groupone.SpyGlass.repos.GoalRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GoalController {


    private final GoalRepo goalRepo;

    public GoalController(GoalRepo goalRepo) {
        this.goalRepo = goalRepo;
    }

    @GetMapping("/goal")
    Collection<Goal> getGoals() {
        return goalRepo.findAll();
    }

    @GetMapping("/goal/{id}")
    ResponseEntity<?> getGoals(@PathVariable Long id) {
        Optional<Goal> goal = goalRepo.findById(id);
        return goal.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/goal")
    ResponseEntity<Goal> addGoals(@RequestBody Goal goal) throws URISyntaxException {
        Goal savedGoal = goalRepo.save(goal);
        return ResponseEntity.created(new URI("/api/goal"+ savedGoal.getId())).body(savedGoal);
    }

    @PutMapping("/goal/{id}")
    ResponseEntity<Goal> updateGoal(@RequestBody Goal goal) {
        Goal currentGoal = goalRepo.save(goal);
        return ResponseEntity.ok().body(currentGoal);

    }
    @DeleteMapping("/goal/{id}")
    public ResponseEntity<?> deleteGoal(@PathVariable Long id) {
        goalRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
