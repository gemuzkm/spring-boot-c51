package com.example.springbootc51.dao;

import java.util.List;

public interface UserDAO<T> {
    void save(T t);
    T findById(long id);
    void remove(T t);
    void update(T t);
    List<T> findAllByName(String name);
    List<T> findAll();
    T findByUsername(String username);
}
