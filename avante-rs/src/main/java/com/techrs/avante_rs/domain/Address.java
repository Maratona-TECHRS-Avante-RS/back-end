package com.techrs.avante_rs.domain;

import com.techrs.avante_rs.security.domain.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "adresses")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

    private String description;
    @Column(name = "coordinate_x")
    private String coordinateX;
    @Column(name = "coordinate_y")
    private String coordinateY;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private User user;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Help help;
}
