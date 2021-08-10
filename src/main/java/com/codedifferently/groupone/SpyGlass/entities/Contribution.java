package com.codedifferently.groupone.SpyGlass.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Contribution {
    @Id
    @GeneratedValue
    private Long id;
    private Date contributionDate;
    private double amount;
    @JsonIgnore
    @ManyToOne
    private Goal goal;

    public Date getContributionDate() {
        return contributionDate;
    }

    public void setContributionDate(Date contributionDate) {
        this.contributionDate = contributionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "Contribution{" +
                "id=" + id +
                ", contributionDate=" + contributionDate +
                ", amount=" + amount +
                ", goal=" + goal +
                '}';
    }
}
