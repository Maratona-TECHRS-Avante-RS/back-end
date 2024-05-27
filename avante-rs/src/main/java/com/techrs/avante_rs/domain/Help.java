package com.techrs.avante_rs.domain;

import com.techrs.avante_rs.security.domain.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "helps")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Help extends BaseEntity {

    private String description;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userCreator;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "urgency_type_id")
    private Answer urgencyType;
    @ManyToOne
    @JoinColumn(name = "help_type_id")
    private Answer helpType;
    @ManyToOne
    @JoinColumn(name = "expiration_time_id")
    private Answer expirationTime;
    @ManyToOne
    @JoinColumn(name = "number_volunteers_id")
    private Answer numberVolunteers;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "helps_tools_types",
            joinColumns = @JoinColumn(name = "help_id"),
            inverseJoinColumns = @JoinColumn(name = "tool_type_id")
    )
    private List<ToolsType> toolsTypeList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "users_helps",
            joinColumns = @JoinColumn(name = "help_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> voluntaryUsers = new ArrayList<>();


    public void addVolunteer(User user){
        this.voluntaryUsers.add(user);
        user.getVoluntaryHelps().add(this);
    }

    public void removeVolunteer(User user){
        this.voluntaryUsers.remove(user);
        user.getVoluntaryHelps().remove(this);
    }

    public void addAddress(Address address) {
        this.setAddress(address);
        address.setHelp(this);
    }

    public String getCoordinateX() {
        if(this.getAddress() != null){
            if(this.getAddress().getCoordinateX() != null){
                return this.address.getCoordinateX();
            }
        }

        return this.userCreator.getAddress().getCoordinateX();
    }

    public String getCoordinateY() {
        if(this.getAddress() != null){
            if(this.getAddress().getCoordinateY() != null){
                return this.address.getCoordinateY();
            }
        }

        return this.userCreator.getAddress().getCoordinateY();
    }

    public String getDescriptionDefaultAddress() {
        if(this.getAddress() != null){
            if(this.getAddress().getDescription() != null){
                return this.address.getDescription();
            }
        }

        return this.userCreator.getAddress().getDescription();
    }

    public String getDescriptionAddress() {
        if(this.getAddress() == null) return null;
        return this.address.getDescription();
    }

}
