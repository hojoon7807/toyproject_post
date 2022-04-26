package com.flab.posttoy.web.mapper;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.web.dto.request.RequestUserDTO;
import com.flab.posttoy.web.dto.response.ResponseUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebUserMapper {
    User toUser(RequestUserDTO requestUserDTO);
    ResponseUserDTO toResponseUser(User user);
}
