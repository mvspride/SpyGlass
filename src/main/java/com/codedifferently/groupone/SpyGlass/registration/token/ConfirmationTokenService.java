package com.codedifferently.groupone.SpyGlass.registration.token;

import com.codedifferently.groupone.SpyGlass.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }

    public Optional<ConfirmationToken> getToken(User user){
        return confirmationTokenRepository.findByUser(user);
    }

    public int setConfirmationTime(String token){
        return confirmationTokenRepository.updateConfirmationTime(token, LocalDateTime.now());
    }

    public int setExpirationTime(String token){
        return confirmationTokenRepository.updateExpirationTime(token, LocalDateTime.now().plusMinutes(1440));
    }
}
