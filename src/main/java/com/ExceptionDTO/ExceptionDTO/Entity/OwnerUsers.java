package com.ExceptionDTO.ExceptionDTO.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OwnerUsers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String username;


    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String  password;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "owner_roles",
    joinColumns = @JoinColumn(name = "owner_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id" ,referencedColumnName = "id")
    )

    private Set<Role> role;

}
