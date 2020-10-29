package com.ecutb.assignment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_acl", joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "acl_id"))
    private User user;
    private String role;


}