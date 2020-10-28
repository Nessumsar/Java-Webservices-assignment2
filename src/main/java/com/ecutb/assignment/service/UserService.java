package com.ecutb.assignment.service;

import com.ecutb.assignment.entity.User;
import com.ecutb.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<String> getAll(String username, boolean reverseSort){
        var users = userRepository.findAll();

        List<String> result = new ArrayList<>();
        users.forEach(user -> {
            if (username != null){
                if (user.getUsername().equals(username)){
                    result.add(user.getUsername());
                }
            }else{
                result.add(user.getUsername());
            }
        });

        //Already sorted by natural order as default
        if(reverseSort){
            result.sort(Comparator.reverseOrder());
        }
        return result;
    }

    public User findByUsername(String username){
        var user = userRepository.findById(username);
        return user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Could not find user by username")));
    }
}
