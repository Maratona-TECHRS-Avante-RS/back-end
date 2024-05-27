package com.techrs.avante_rs.dto.mapApi.MapApiCore;

import lombok.Getter;

import java.util.List;

@Getter
public class Result {

    private List<AddressComponent> addressComponents;
    private String formattedAddress;

    private Geometry geometry;

    private String placeId;

    private PlusCode plusCode;
    private List<String> types;
}
