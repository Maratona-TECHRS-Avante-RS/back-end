package com.techrs.avante_rs.dto.help;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResumeHelpCreatorResponse {

    private Long id;
    private Integer activeVolunteers;
    private String createdAt;
    private String address;
    private String description;
    private String helpTypeLabel;
    private String urgencyLevelLabel;
    private String expirationTimeLabel;
    private String numberVolunteersLabel;
}
