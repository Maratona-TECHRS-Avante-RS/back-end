package com.techrs.avante_rs.dto.mapApi.MapApiCore;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MapApiResponse {

    private List<Result> results;
    private String status;

}
