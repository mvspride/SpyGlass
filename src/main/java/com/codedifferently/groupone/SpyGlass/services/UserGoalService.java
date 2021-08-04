package com.codedifferently.groupone.SpyGlass.services;

import com.codedifferently.groupone.SpyGlass.entities.Goal;
import com.codedifferently.groupone.SpyGlass.entities.User;
import com.codedifferently.groupone.SpyGlass.exceptions.goal.GoalNotFoundException;
import com.codedifferently.groupone.SpyGlass.exceptions.user.UserNotFoundException;
import com.codedifferently.groupone.SpyGlass.repos.GoalRepo;
import com.codedifferently.groupone.SpyGlass.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGoalService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GoalRepo goalRepo;

    public void addGoalToUser(Long userId, Long goalId){
        Goal currentGoal = goalRepo.findById(userId).orElseThrow(()-> new GoalNotFoundException(goalId));
        User currentUser = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        currentUser.addGoal(currentGoal);
        userRepo.save(currentUser);
        goalRepo.save(currentGoal);
    }
    public void deleteGoalFromUser(Long userId, Long goalId){
        Goal currentGoal = goalRepo.findById(userId).orElseThrow(()-> new GoalNotFoundException(goalId));
        User currentUser = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        currentUser.deleteGoal(currentGoal);
        userRepo.save(currentUser);
        goalRepo.delete(currentGoal);
    }



}

