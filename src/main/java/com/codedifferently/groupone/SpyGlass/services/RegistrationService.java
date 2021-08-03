package com.codedifferently.groupone.SpyGlass.services;


import com.codedifferently.groupone.SpyGlass.email.EmailSender;
import com.codedifferently.groupone.SpyGlass.entities.User;
import com.codedifferently.groupone.SpyGlass.enums.UserRole;
import com.codedifferently.groupone.SpyGlass.registration.RegistrationRequest;
import com.codedifferently.groupone.SpyGlass.registration.token.ConfirmationToken;
import com.codedifferently.groupone.SpyGlass.registration.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailSender emailSender;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email is not valid");
        }
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());
        newUser.setUserRole(UserRole.USER);
        String token = userService.signUpUser(newUser);
        String link = "http://localhost:8080/registration/confirm?token=" + token;
        emailSender.send(request.getEmail(), "Please confirm your email for SpyGlass by clicking the following link: \n " + link);
        return token;
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));
        if(confirmationToken.getConfirmationTime() != null){
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expirationTime = confirmationToken.getExpirationTime();
        if(expirationTime.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmationTime(token);
        userService.enableUser(confirmationToken.getUser().getEmail());

        return "confirmed";
    }
}
