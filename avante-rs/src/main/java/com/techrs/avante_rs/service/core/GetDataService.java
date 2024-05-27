package com.techrs.avante_rs.service.core;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetDataService {

    public LocalDateTime getNowDateAndTime(){
        return LocalDateTime.now();
    }
}
