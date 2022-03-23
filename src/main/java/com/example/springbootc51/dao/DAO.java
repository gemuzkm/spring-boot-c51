package com.example.springbootc51.dao;

import java.util.List;

public interface DAO<T> {
    void save(T t);
    void remove(T t);
    void update(T t);
    List<T> findAll();
}
