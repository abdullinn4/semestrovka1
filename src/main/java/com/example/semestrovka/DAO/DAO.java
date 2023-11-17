package com.example.semestrovka.DAO;

import java.util.List;

public interface DAO<T> {
    //CRUD
    void create(T u);
    T get(long id);
    void update(T u);
    void delete(T u);
    List<T> getAll();
}
