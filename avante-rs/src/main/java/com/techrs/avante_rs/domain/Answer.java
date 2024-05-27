package com.techrs.avante_rs.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "answers")
public class Answer extends BaseEntity {

    private String name;
    @Column(name = "type_question", columnDefinition = "int4")
    @Enumerated(EnumType.ORDINAL)
    private TypeQuestion typeQuestion;

    @OneToMany(mappedBy = "urgencyType")
    private List<Help> helpsByUrgencyType;

    @OneToMany(mappedBy = "helpType")
    private List<Help> helpsByHelpType;

    @OneToMany(mappedBy = "expirationTime")
    private List<Help> helpsByExpirationTime;

    @OneToMany(mappedBy = "numberVolunteers")
    private List<Help> helpsByNumberVolunteers;

}
