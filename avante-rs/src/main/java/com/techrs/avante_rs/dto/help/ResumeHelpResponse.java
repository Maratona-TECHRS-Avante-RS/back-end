package com.techrs.avante_rs.dto.help;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ResumeHelpResponse {

    private Long id;
    private Integer activeVolunteers;
    private String createdAt;
    private String address;
    private String distance;
    private boolean isUserVoluntary;
    private String description;
    private String helpTypeLabel;
    private String urgencyLevelLabel;
    private String expirationTimeLabel;
    private String numberVolunteersLabel;
}
