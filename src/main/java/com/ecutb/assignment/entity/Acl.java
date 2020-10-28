package com.ecutb.assignment.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "acl")
public class Acl implements Serializable {
    private static final long serialVersionUID = 149346424537123620L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_acl", joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "acl_id"))
    private User user;
    private String role;


}