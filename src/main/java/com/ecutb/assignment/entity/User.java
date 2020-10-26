package com.ecutb.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    String username;
    String password;
    Role role;
}
