package SSM.mapper;

import SSM.domain.User;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;

public interface UserMapper {
   @Select("SELECT * FROM user WHERE username=#{username} AND password = #{password}")

   User findLoginUser(@Param("username") String username, @Param("password") String password);
   @Insert("insert into user(username,email,password,name,phone,address,isadmin,isvalidate) values(#{username},#{email},#{password},#{name},#{phone},#{address},#{isadmin},#{isvalidate})")
   void addUser(User user);
   @Select("SELECT COUNT(*) > 0 FROM user WHERE username = #{username}")
   boolean isUsernameExist(String username);

   @Select("SELECT COUNT(*) > 0 FROM user WHERE email = #{email}")
   boolean isEmailExist(String email);
   @Update("update user set name = #{name},phone=#{phone},address=#{address} where id = #{id}")
   void updateUserAddress(User user);

   @Update("update user set password = #{password} where id = #{id}")
   void updatePwd(User user);

    @Delete("delete from user where id = #{id}")
    void delete(int id) throws SQLException;

    @Select("select password from user where id = #{id}")
    String getPasswordById(User user);
}
