package com.flab.posttoy.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

public interface GenericMapper <D,E>{
    D toDto(E e);
    E toEntity(D d);
}
