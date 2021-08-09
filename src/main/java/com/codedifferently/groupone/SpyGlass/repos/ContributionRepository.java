package com.codedifferently.groupone.SpyGlass.repos;

import com.codedifferently.groupone.SpyGlass.entities.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {
}
