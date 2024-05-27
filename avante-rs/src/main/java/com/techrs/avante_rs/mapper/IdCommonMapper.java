package com.techrs.avante_rs.mapper;

import com.techrs.avante_rs.domain.Help;
import com.techrs.avante_rs.dto.IdResponse;
import com.techrs.avante_rs.security.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IdCommonMapper {

    public static IdResponse toIdResponse(Help help){
        return IdResponse.builder().id(help.getId()).build();
    }
    public static IdResponse toIdResponse(User user){
        return IdResponse.builder().id(user.getId()).build();
    }
}
