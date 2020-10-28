package com.ecutb.assignment.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 7651373803996797571L;
    @Id
    private String username;
    @NotEmpty(message = "Password cannot be empty.")
    @Size(min = 5, max = 36, message = "Password must consist of 7 to 36 characters.")
    private String password;

    //@ElementCollection
    //private List<Acl> acl;

   /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_acl",
            joinColumns = @JoinColumn(name = "username_id"),
            inverseJoinColumns = @JoinColumn(name = "acl_id")
    )
    private List<Acl> acl;
    */
}
