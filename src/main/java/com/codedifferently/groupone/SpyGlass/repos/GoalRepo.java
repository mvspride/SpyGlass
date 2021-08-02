package com.codedifferently.groupone.SpyGlass.repos;

import com.codedifferently.groupone.SpyGlass.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepo extends JpaRepository<Goal, Long> {
}
