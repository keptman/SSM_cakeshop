package SSM.service.impl;

import SSM.domain.Type;
import SSM.mapper.TypeMapper;
import SSM.service.TypeService;
import jakarta.annotation.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service("typeService")
public class TypeServiceImpl implements TypeService {
    @Resource(name = "typeMapper")
    private TypeMapper typeMapper;
    @Override
    public List<Type> selectAll() {
        List<Type> typeList = typeMapper.selectAll();
        return typeList;

    }

    @Override
    public void insert(Type t) {
        typeMapper.insert(t);
    }

    @Override
    public Type select(int id) {
        return  typeMapper.select(id);
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public boolean delete(int id) {
        try {
            typeMapper.delete(id);
            return true;
        }catch (DataIntegrityViolationException | SQLException e) {
            return false;
        }
    }
}
