package com.codedifferently.groupone.SpyGlass.repos;

import com.codedifferently.groupone.SpyGlass.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
//    @Transactional
//    @Modifying
//    @Query("UPDATE USER user SET user.username = ?1")
//    int updateUser(String username);
}


