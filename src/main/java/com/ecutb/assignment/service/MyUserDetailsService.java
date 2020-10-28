package com.ecutb.assignment.service;

import com.ecutb.assignment.entity.User;
import com.ecutb.assignment.repository.AclRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;
    @Autowired
    private AclRepository aclRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Username " + username + " not found.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
        return user.getAcl().stream()
                .map(acl -> new SimpleGrantedAuthority("ROLE_"+acl.getRole()))
                .collect(Collectors.toList());
    }


}

