package com.codedifferently.groupone.SpyGlass.exceptions.goal;

public class GoalNotFoundException extends RuntimeException{
    public GoalNotFoundException(Long goalId) {
        super(goalId + " not found");
    }
}
