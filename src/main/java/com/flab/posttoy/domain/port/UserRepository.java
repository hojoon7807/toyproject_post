package com.flab.posttoy.domain.port;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface UserRepository <T,ID extends Serializable>{
    T insert(T t);
    void delete(ID id);
    Optional<T> selectById(ID id);
    Optional<T> selectByUsername(String username);
    List<T> selectAll();
}
