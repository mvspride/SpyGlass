package com.codedifferently.groupone.SpyGlass.repos;

import com.codedifferently.groupone.SpyGlass.entities.Goal;
import com.codedifferently.groupone.SpyGlass.entities.User;
import com.codedifferently.groupone.SpyGlass.enums.Frequency;
import com.codedifferently.groupone.SpyGlass.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface GoalRepo extends JpaRepository<Goal, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Goal g SET g.description = ?1, g.deadLine = ?2, g.frequency = ?3," +
            " g.priority = ?4, g.contributionAmount = ?5, g.goalAmount = ?6, g.currentlySaved = ?7," +
            " g.pictureURL = ?8 WHERE g.id = ?9")
    int updateGoal(String description, Date deadLine, Frequency frequency,
                             Priority priority, double contributionAmount,
                             double goalAmount, double currentlySaved, String pictureUrl, Long id);

    List<Goal> findAllByUser(User user);
}
