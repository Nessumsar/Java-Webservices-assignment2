package com.ecutb.assignment.controller;


import com.ecutb.assignment.entity.User;
import com.ecutb.assignment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<User> saveUser(@RequestBody User user){
       return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllUsers(@RequestParam(required = false) String name,
                                                    @RequestParam(required = false) boolean reverseSort){
        return ResponseEntity.ok(userService.getAll(name, reverseSort));
    }
}
