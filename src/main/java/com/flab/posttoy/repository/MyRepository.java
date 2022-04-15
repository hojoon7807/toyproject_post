package com.flab.posttoy.repository;

import java.util.List;
import java.util.Optional;

public interface MyRepository<ID, T>{
    T save(T t);
    Optional<T> findById(ID id);
    Optional<T> findByName(String name);
    List<T> findAll();
}
