package com.example.geektrust.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T, ID> {

    T save(T object);

    boolean existById(ID id);

    Optional<T> findById(ID id);

    void delete(T object);
    void deleteById(ID id);

    List<T> findAll();
}
