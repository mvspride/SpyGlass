package com.codedifferently.groupone.SpyGlass.services;


import com.codedifferently.groupone.SpyGlass.email.EmailSender;
import com.codedifferently.groupone.SpyGlass.entities.User;
import com.codedifferently.groupone.SpyGlass.registration.token.ConfirmationToken;
import com.codedifferently.groupone.SpyGlass.registration.token.ConfirmationTokenService;
import com.codedifferently.groupone.SpyGlass.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with email %s not found";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(User user){
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExists){
            if(!user.getEnabled()){
                User savedUser = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new IllegalStateException("user not found"));
                ConfirmationToken userToken = confirmationTokenService.getToken(savedUser).orElseThrow(() -> new IllegalStateException("token not found"));
                confirmationTokenService.setExpirationTime(userToken.getToken());
            }
            throw new IllegalStateException("an account with that email already exists, if the account hasn't been activated then a confirmation email will be resent");
        }
        else {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
        String token = generateToken(user);
        return token;

    }

    public String generateToken(User user){
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setCreationTime(LocalDateTime.now());
        confirmationToken.setExpirationTime(LocalDateTime.now().plusMinutes(1440));
        confirmationToken.setUser(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public int enableUser(String email){
        return userRepository.enableUser(email);
    }
}
