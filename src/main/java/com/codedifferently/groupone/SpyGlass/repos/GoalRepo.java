package com.codedifferently.groupone.SpyGlass.repos;

import com.codedifferently.groupone.SpyGlass.entities.Goal;
import com.codedifferently.groupone.SpyGlass.enums.Frequency;
import com.codedifferently.groupone.SpyGlass.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface GoalRepo extends JpaRepository<Goal, Long> {
//    @Transactional
//    @Modifying
//    @Query("UPDATE GOAL goal SET goal.description = ?1 WHERE goal.deadLine = ?2 WHERE goal.frequency = ?3 " +
//            "WHERE goal.priority = ?4 WHERE goal.contributionAmount = ?5 WHERE goal.goalAmount = ?6 WHERE goal.currentlySaved = ?7" +
//            "WHERE goal.pictureUrl = ?8")
//    int updateGoal(String description, Date deadLine, Frequency frequency,
//                             Priority priority, double contributionAmount,
//                             double goalAmount,double currentlySaved, String pictureUrl);
}
