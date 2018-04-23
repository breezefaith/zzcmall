package cn.breezefaith.service;

import cn.breezefaith.entity.Address;
import cn.breezefaith.entity.User;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    String login(String username, String password);

    String register(String username, String password, String phone, String email);

    boolean checkUsernameAvailable(String username);

    boolean checkPhoneAvailable(String phone);

    boolean checkEmailAvailable(String email);

    User getPersonInfo(String token) throws IOException;

    boolean updatePersonInfo(String token, String phone, String email) throws IOException;

    boolean updatePassword(String token, String originPassword, String newPassword) throws IOException;

    User isLogged(String token) throws IOException;

    void logout(String token);

    List<Address> getAddresses(String token);
}
