package SSM.service.impl;
import SSM.domain.User;
import SSM.mapper.UserMapper;
import SSM.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Override
    public User findLonginUser(String username, String password) {
        User user = userMapper.findLoginUser(username, password);
        return user;
    }


    @Override
    public boolean register(User user) {
       if (userMapper.isUsernameExist(user.getUsername())){
           return false;
       }
       if (userMapper.isEmailExist(user.getEmail())){
           return false;
       }
       userMapper.addUser(user);
       return true;
    }

    @Override
    public void updateUserAddress(User user) {
        userMapper.updateUserAddress(user);
    }

    @Override
    public void updatePwd(User user) {
        userMapper.updatePwd(user);
    }

    @Override
    public boolean delete(int id) {
        try {
            userMapper.delete(id);
            return true;
        }catch (DataIntegrityViolationException | SQLException e) {
            return false;
        }
    }

    @Override
    public String getPasswordById(User user) {
        userMapper.getPasswordById(user);
        return  userMapper.getPasswordById(user);
    }

}

