package SSM.service;

import SSM.domain.User;



public interface UserService {
    User findLonginUser(String username,String password);

    boolean register(User user);

    void updateUserAddress(User user);

    void updatePwd(User user);

    boolean delete(int id);

    String getPasswordById(User user);

}
