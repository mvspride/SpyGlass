package com.codedifferently.groupone.SpyGlass.registration.token;

import com.codedifferently.groupone.SpyGlass.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    Optional<ConfirmationToken> findByUser(User user);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.expirationTime = ?2 WHERE c.token = ?1")
    int updateExpirationTime(String token, LocalDateTime expirationTime);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmationTime = ?2 WHERE c.token = ?1")
    int updateConfirmationTime(String token, LocalDateTime confirmationTime);
}
