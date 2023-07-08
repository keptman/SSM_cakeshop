package SSM.mapper;

import SSM.domain.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;

public interface TypeMapper {

   @Select("select * from type")
   List<Type> selectAll();

   @Select("select * from type where id = #{id} ")
   Type select(int id);

   @Select("insert into type(name) values(#{name})")
   void insert(Type t);

   @Update("update type set name=#{name} where id = #{id}")
   void update(Type type);

   @Delete("delete from type where id = #{id}")
   void delete(int id) throws SQLException;
}
