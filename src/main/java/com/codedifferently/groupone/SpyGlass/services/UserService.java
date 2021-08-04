package com.codedifferently.groupone.SpyGlass.services;

import com.codedifferently.groupone.SpyGlass.entities.User;
import com.codedifferently.groupone.SpyGlass.exceptions.user.UserNotFoundException;
import com.codedifferently.groupone.SpyGlass.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    /**
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * @param id
     * @return user found by id
     * @throws UserNotFoundException
     */
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        return userRepo.findById(id).get();
    }

    
    /**
     * creates a new users
     * @param user
     * @throws URISyntaxException
     */
    public ResponseEntity addUser(@RequestBody User user) throws URISyntaxException {
        User newUser = userRepo.save(user);
        return ResponseEntity.created(new URI("/goals" + newUser.getId())).body(newUser);
    }

}
