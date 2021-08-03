package com.codedifferently.groupone.SpyGlass.controllers;

import com.codedifferently.groupone.SpyGlass.entities.User;
import com.codedifferently.groupone.SpyGlass.repos.UserRepo;
import com.codedifferently.groupone.SpyGlass.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {


    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/user")
    List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user")
    ResponseEntity<User> addUser(@RequestBody User user) throws URISyntaxException {
        User savedUser = userRepo.save(user);
        return ResponseEntity.created(new URI("/api/user"+ savedUser.getId())).body(savedUser);
    }

    @PutMapping("/user/{id}")
    ResponseEntity<User> updateGoalToUser(@RequestBody User user) {
        User currentUser = userRepo.save(user);
        return ResponseEntity.ok().body(currentUser);

    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteGoalFromUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
