package com.techrs.avante_rs.dto.mapApi.MapApiCore;

import lombok.Getter;

@Getter
public class Geometry {
    private Location location;

    private String locationType;

    private Viewport viewport;

}
