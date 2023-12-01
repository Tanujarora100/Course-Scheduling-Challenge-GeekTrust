package repositoryservice;

import exception.DataValidationException;

import java.util.List;
import java.util.Optional;

public interface CRUDRepositoryService <T,ID>{
    T save(T object) throws DataValidationException;
    List<T> findAll();
    public Optional<T> findById(ID id);
    boolean existByID(ID id);
    void delete(T object) throws DataValidationException;
    void deleteByID(ID id);
    boolean validateID(ID id);

}
