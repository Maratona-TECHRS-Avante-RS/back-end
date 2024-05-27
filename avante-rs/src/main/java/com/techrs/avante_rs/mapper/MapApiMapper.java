package com.techrs.avante_rs.mapper;

import com.techrs.avante_rs.dto.mapApi.CoordinateResponse;
import com.techrs.avante_rs.dto.mapApi.MapApiCore.Location;
import com.techrs.avante_rs.dto.mapApi.MapApiCore.MapApiResponse;
import com.techrs.avante_rs.dto.mapApi.MapApiCore.Result;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MapApiMapper {

    public static CoordinateResponse toCoordinateResponse(MapApiResponse response){
        if (response.getResults() != null && !response.getResults().isEmpty()) {

            Result firstResult = response.getResults().get(0);
            Location location = firstResult.getGeometry().getLocation();

            return CoordinateResponse
                    .builder()
                    .axleX(Double.toString(location.getLng()))
                    .axleY(Double.toString(location.getLat()))
                    .build();
        } else {
            return CoordinateResponse.builder().build();
        }
    }

}
