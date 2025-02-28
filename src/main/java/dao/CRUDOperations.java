package dao;

import java.util.List;

public interface CRUDOperations<E> {
    List<E> getAll(int size, int page);
}
