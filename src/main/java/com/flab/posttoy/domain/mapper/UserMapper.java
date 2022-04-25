package com.flab.posttoy.domain.mapper;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.dto.UserDTO;
import org.mapstruct.Mapper;

// spring 빈으로 등록해준다.
// Mapper가 자동으로 구현체를 만들어줌
@Mapper(componentModel = "spring")
public interface UserMapper{
    //to dto
    UserDTO toUserDto(User user);
    //to entity
    User toUser(UserDTO userDTO);
}
