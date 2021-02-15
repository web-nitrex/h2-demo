package com.example.h2demo.dao;

import java.util.List;
import java.util.Optional;

public interface DAO <T> {

    List<T> list();

    void create(T t);

    Optional<T> get(long id);

    void update(T t, long id);

    void delete(long id);

}
