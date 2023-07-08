package SSM.service;

import SSM.domain.Type;

import java.util.List;

public interface TypeService {
    List<Type> selectAll();
    void insert(Type t);
    Type select(int id);

    void update(Type type);

    boolean delete(int id);
}
