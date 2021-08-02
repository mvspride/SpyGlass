package com.codedifferently.groupone.SpyGlass.services;

import com.codedifferently.groupone.SpyGlass.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
}
