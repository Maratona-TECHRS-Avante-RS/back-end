package com.techrs.avante_rs.service;

import com.techrs.avante_rs.domain.*;
import com.techrs.avante_rs.dto.IdResponse;
import com.techrs.avante_rs.dto.help.*;
import com.techrs.avante_rs.dto.mapApi.CoordinateResponse;
import com.techrs.avante_rs.mapper.HelpMapper;
import com.techrs.avante_rs.mapper.IdCommonMapper;
import com.techrs.avante_rs.repositories.HelpRepository;
import com.techrs.avante_rs.repositories.ToolsTypeRepository;
import com.techrs.avante_rs.security.domain.User;
import com.techrs.avante_rs.security.service.AuthenticatedUserService;
import com.techrs.avante_rs.service.core.GetDataService;
import com.techrs.avante_rs.service.core.ValidAnswerService;
import com.techrs.avante_rs.service.core.ValidHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class HelpService {
    @Autowired
    private HelpRepository helpRepository;
    @Autowired
    private GetDataService getDataService;
    @Autowired
    private AuthenticatedUserService authenticatedUserService;
    @Autowired
    private ValidAnswerService validAnswerService;
    @Autowired
    private ValidHelpService validHelpService;
    @Autowired
    private ToolsTypeRepository toolsTypeRepository;
    @Autowired
    private MapApiService mapApiService;

    @Transactional
    public void delete(Long id) {
        validHelpService.isUserCreatorByIdHelp(id);
        helpRepository.deleteById(id);
    }

    @Transactional
    public IdResponse create(CreateHelpRequest request) {
        Help help = HelpMapper.toHelp(request);
        help.setCreatedAt(getDataService.getNowDateAndTime());
        help.setUserCreator(authenticatedUserService.getUser());

        setCustomAddress(request, help);
        getAndSetAllAnswers(request, help);

        helpRepository.save(help);
        return IdCommonMapper.toIdResponse(help);
    }

    @Transactional
    public IdResponse edit(Long id, CreateHelpRequest request) {
        Help help = validHelpService.getHelpById(id);
        validHelpService.isUserCreatorByHelp(help);
        HelpMapper.editHelp(help, request);

        setCustomAddress(request, help);
        getAndSetAllAnswers(request, help);

        helpRepository.save(help);
        return IdCommonMapper.toIdResponse(help);
    }

    @Transactional
    public IdResponse getInHelp(Long id) {
        Help help = validHelpService.getHelpById(id);
        User user = authenticatedUserService.getUser();

        if(help.getVoluntaryUsers().contains(user)){
            help.removeVolunteer(user);
        }else{
            help.addVolunteer(user);
        }

        helpRepository.save(help);
        return IdCommonMapper.toIdResponse(help);
    }

    public CompleteHelpResponse getById(Long id) {
        Help help = validHelpService.getHelpById(id);
        User user = authenticatedUserService.getUser();

        double distanceInKm = getDistanceOfCoordinatesInKm(
                user.getAddress().getCoordinateX(),
                user.getAddress().getCoordinateY(),
                help.getCoordinateX(),
                help.getCoordinateY());

        return HelpMapper.toCompleteResponse(help, distanceInKm, user);
    }

    public Page<ResumeHelpResponse> getAll(Long urgencyLevelId, Long helpTypeId, Long distance, Pageable pageable) {
        Long userId = authenticatedUserService.getIdUser();
        User user = authenticatedUserService.getUser();
        LocalDateTime thresholdDate = LocalDateTime.now().minus(12, ChronoUnit.DAYS);

        return helpRepository
                .findAllWithParams(urgencyLevelId, helpTypeId, distance, userId, thresholdDate, pageable)
                .map(help -> {
                    double distanceInKm = getDistanceOfCoordinatesInKm(
                            user.getAddress().getCoordinateX(),
                            user.getAddress().getCoordinateY(),
                            help.getCoordinateX(),
                            help.getCoordinateY());

                    boolean isUserVoluntary = help.getVoluntaryUsers().contains(user);

                    return HelpMapper.toResumeHelpResponse(help, distanceInKm, isUserVoluntary);
                }
        );
    }

    private double getDistanceOfCoordinatesInKm(String x1, String y1, String x2, String y2) {
        final int earthRadius = 6371;

        double lat1 = Double.parseDouble(x1);
        double lon1 = Double.parseDouble(y1);
        double lat2 = Double.parseDouble(x2);
        double lon2 = Double.parseDouble(y2);

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
    private void setCustomAddress(CreateHelpRequest request, Help help){
        if(request.getCustomAddress() == null) return;
        if(request.getCustomAddress().isBlank()) return;

        Address address = new Address();
        CoordinateResponse addressCoordinates =
                mapApiService.getCoordinateFromAddress(request.getCustomAddress());
        address.setDescription(request.getCustomAddress());
        address.setCoordinateX(addressCoordinates.getAxleX());
        address.setCoordinateY(addressCoordinates.getAxleY());
        help.addAddress(address);
    }

    private void getAndSetAllAnswers(CreateHelpRequest request, Help help){
        Answer urgencyType =
                validAnswerService.getAnswerByIdAndType(request.getUrgencyType(), TypeQuestion.URGENCY_LEVEL);
        Answer helpType =
                validAnswerService.getAnswerByIdAndType(request.getHelpType(), TypeQuestion.HELP_TYPE);
        Answer expirationTime =
                validAnswerService.getAnswerByIdAndType(request.getExpirationTime(), TypeQuestion.EXPIRATION_TIME);
        Answer numberVolunteers =
                validAnswerService.getAnswerByIdAndType(request.getNumberVolunteers(), TypeQuestion.NUMBER_VOLUNTEERS);
        List<ToolsType> toolsTypeList = toolsTypeRepository.findAllById(request.getIdsTools());

        help.setUrgencyType(urgencyType);
        help.setHelpType(helpType);
        help.setExpirationTime(expirationTime);
        help.setNumberVolunteers(numberVolunteers);
        help.setToolsTypeList(toolsTypeList);
    }

    public Page<ResumeHelpCreatorResponse> getAllByUser(Pageable pageable) {
        Long idUser = authenticatedUserService.getIdUser();

        return helpRepository.findAllByUserCreatorId(idUser, pageable)
                .map(HelpMapper::toResumeCreatorResponse);
    }
}

