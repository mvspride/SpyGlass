package com.codedifferently.groupone.SpyGlass.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @OneToMany
    private List<Goal> goals;

    public User() {
    }

    public void addGoal(Goal goal){
        goals.add(goal);
    }

    public void deleteGoal(Goal goal){
        goals.remove(goal);
    }

    public Goal getGoalById(Long goalId){
        for (Goal goal: goals){
           // if(goal.)
            return goal;
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}
