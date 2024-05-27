package com.techrs.avante_rs.service;

import com.techrs.avante_rs.dto.mapApi.CoordinateResponse;
import com.techrs.avante_rs.dto.mapApi.MapApiCore.MapApiResponse;
import com.techrs.avante_rs.mapper.MapApiMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@Service
public class MapApiService {

    @Value("${values.apiMap.key}")
    private String apiMapKey;
    @Value("${values.apiMap.url}")
    private String apiMapUrl;

    public CoordinateResponse getCoordinateFromAddress(String addressDescription){
        RestTemplate restTemplate = new RestTemplate();

        try{
        String apiMapUrlComplete = apiMapUrl + "address=" + addressDescription + "&key=" + apiMapKey;
        ResponseEntity<MapApiResponse> response =
                restTemplate.getForEntity(apiMapUrlComplete, MapApiResponse.class);

            if(response.getBody() != null){
                return MapApiMapper.toCoordinateResponse(response.getBody());
            }
            return CoordinateResponse.builder().build();

        }catch (Exception error){
            throw new ResponseStatusException(SERVICE_UNAVAILABLE, "Erro ao buscar seu endereço no mapa");
        }


    }

}
