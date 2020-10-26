package com.ecutb.assignment.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class User {

    @Id
    private String username;
    @NotEmpty(message = "Password cannot be empty.")
    @Size(min = 5, max = 36, message = "Password must consist of 7 to 36 characters.")
    private String password;
    private List<String> acl;
}
