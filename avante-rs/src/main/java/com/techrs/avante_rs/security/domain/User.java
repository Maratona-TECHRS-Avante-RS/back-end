package com.techrs.avante_rs.security.domain;

import com.techrs.avante_rs.domain.Address;
import com.techrs.avante_rs.domain.BaseEntity;
import com.techrs.avante_rs.domain.Help;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "users")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(unique = true)
    private String email;
    private String password;
    private String cpf;
    private String name;

    @Enumerated(STRING)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "userCreator", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Help> helpsCreated = new ArrayList<>();

    @ManyToMany(mappedBy = "voluntaryUsers")
    private List<Help> voluntaryHelps = new ArrayList<>();

    public void insertAddress(Address address) {
        this.setAddress(address);
        address.setUser(this);
    }
}
