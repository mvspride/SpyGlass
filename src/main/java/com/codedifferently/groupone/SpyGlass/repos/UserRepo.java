package com.codedifferently.groupone.SpyGlass.repos;

import com.codedifferently.groupone.SpyGlass.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
