package com.techrs.avante_rs.dto.mapApi;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CoordinateResponse {
    private String axleX;
    private String axleY;

}
