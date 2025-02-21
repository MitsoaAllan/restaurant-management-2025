package dao;

import java.util.List;

public interface CRUDOperations<E> {
    List<E> getAll(int size, int page);
    E getById(int id);
    List<E> saveAll(List<E> entities);
    E update(int id, E entity);
    void delete(int id);
}
