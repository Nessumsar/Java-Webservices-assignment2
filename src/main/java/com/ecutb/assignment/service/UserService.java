package com.ecutb.assignment.service;

import com.ecutb.assignment.entity.Acl;
import com.ecutb.assignment.entity.User;
import com.ecutb.assignment.repository.AclRepository;
import com.ecutb.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final AclRepository aclRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Acl> aclList = user.getAcl();
        aclList.forEach(aclRepository::save);
        return userRepository.save(user);
    }

    /**
     * Anledningen till att det finns en boolean getAcl här är för att illustrera att det går ut att få ut den roller som användarna får.
     * Problemet är att eftersom det blev väldigt struligt med SQL, lyckades jag inte få Acl att mappas korrekt till User.
     * Acl kunde aldrig bindas till en användare och därför kunde man aldrig få ut en roll bunden till användare vid annat tillfälle än vid skapandet.
     *
     */
    public List<String> getAll(String username, boolean reverseSort, boolean getAcl){
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

        //Get roles
        List<String> aclList = new ArrayList<>();
        var roles = new ArrayList<>();
        var acls = aclRepository.findAll();
        acls.forEach(acl ->  roles.add(acl.getRole()));

        roles.forEach(role -> {
            aclList.add("Role: " + role);
        });

        //Already sorted by natural order as default
        if(reverseSort){
            result.sort(Comparator.reverseOrder());
        }

        if (getAcl && (!reverseSort)){
            return aclList;
        }else{
            return result;
        }
    }

    public User findByUsername(String username){
        var user = userRepository.findById(username);
        return user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("SERVICE Could not find user by username %s", username)));
    }
}
