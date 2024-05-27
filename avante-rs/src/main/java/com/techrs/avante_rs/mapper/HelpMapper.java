package com.techrs.avante_rs.mapper;

import com.techrs.avante_rs.domain.Help;
import com.techrs.avante_rs.dto.answer.AnswerResponse;
import com.techrs.avante_rs.dto.help.CompleteHelpResponse;
import com.techrs.avante_rs.dto.help.CreateHelpRequest;
import com.techrs.avante_rs.dto.help.ResumeHelpCreatorResponse;
import com.techrs.avante_rs.dto.help.ResumeHelpResponse;
import com.techrs.avante_rs.security.domain.User;
import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class HelpMapper {

    // TODO validar o local correto de onde deve ficar este método
    public static void editHelp(Help help, CreateHelpRequest request){
        help.setDescription(request.getDescription());
        help.setPhoneNumber(request.getPhoneNumber());
    }

    public static Help toHelp(CreateHelpRequest request){
        return Help
                .builder()
                .phoneNumber(request.getPhoneNumber())
                .description(request.getDescription())
                .build();
    }

    public static CompleteHelpResponse toCompleteResponse(
            Help help, double distance, User userLogged){
        return CompleteHelpResponse
                .builder()
                .id(help.getId())
                .customAddress(help.getDescriptionAddress())
                .defaultAddress(help.getDescriptionDefaultAddress())
                .createdAt(help.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .activeVolunteers(help.getVoluntaryUsers().size())
                .distance(String.format("%.2f", distance))
                .isUserVoluntary(help.getVoluntaryUsers().contains(userLogged))
                .isUserCreator(help.getUserCreator().equals(userLogged))
                .description(help.getDescription())
                .phoneNumber(help.getPhoneNumber())
                .helpType(AnswerMapper.toResponse(help.getHelpType()))
                .urgencyLevel(AnswerMapper.toResponse(help.getUrgencyType()))
                .expirationTime(AnswerMapper.toResponse(help.getExpirationTime()))
                .numberVolunteers(AnswerMapper.toResponse(help.getNumberVolunteers()))
                .toolsOptions(getToolsAnswers(help))
                .build();
    }
    public static ResumeHelpResponse toResumeHelpResponse(Help help, double distance, boolean isUserVoluntary){
        return ResumeHelpResponse
                .builder()
                .id(help.getId())
                .address(help.getDescriptionDefaultAddress())
                .createdAt(help.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .activeVolunteers(help.getVoluntaryUsers().size())
                .description(help.getDescription())
                .isUserVoluntary(isUserVoluntary)
                .helpTypeLabel(help.getHelpType().getName())
                .distance(String.format("%.2f", distance))
                .urgencyLevelLabel(help.getUrgencyType().getName())
                .expirationTimeLabel(help.getExpirationTime().getName())
                .numberVolunteersLabel(help.getNumberVolunteers().getName())
                .build();
    }


    private List<AnswerResponse> getToolsAnswers(Help help){
        return help.getToolsTypeList()
                .stream()
                .map(AnswerMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static ResumeHelpCreatorResponse toResumeCreatorResponse(Help help) {
        return ResumeHelpCreatorResponse
                .builder()
                .id(help.getId())
                .address(help.getDescriptionDefaultAddress())
                .createdAt(help.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .activeVolunteers(help.getVoluntaryUsers().size())
                .description(help.getDescription())
                .helpTypeLabel(help.getHelpType().getName())
                .urgencyLevelLabel(help.getUrgencyType().getName())
                .expirationTimeLabel(help.getExpirationTime().getName())
                .numberVolunteersLabel(help.getNumberVolunteers().getName())
                .build();
    }
}
