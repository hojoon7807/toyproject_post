package com.flab.posttoy.mapper;

import com.flab.posttoy.domain.User;
import com.flab.posttoy.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.control.MappingControl;

// spring 빈으로 등록해준다.
// Mapper가 자동으로 구현체를 만들어줌
@Mapper(componentModel = "spring")
public interface UserMapper{
    //to entity
    UserEntity toUserEntity(User user);
    //to domain
    User toUser(UserEntity userEntity);
}
