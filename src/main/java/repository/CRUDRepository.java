package repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository <T,ID>{
    void save(T object);
    List<T> findAll();
    public Optional<T> findById(ID id);
    boolean existById(ID id);
    void delete(T object);
    void deleteByID(ID id);


}
