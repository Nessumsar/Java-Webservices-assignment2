package com.ecutb.assignment.service;

import com.ecutb.assignment.entity.User;
import com.ecutb.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<String> getAll(){
        var users = userRepository.findAll();
        List<String> result = new ArrayList<>();
        users.forEach(user -> {
            result.add(user.getUsername());
        });
        return result;
    }

    public User findByUsername(String username){
        var user = userRepository.findById(username);
        return user.orElse(null);
    }
}
