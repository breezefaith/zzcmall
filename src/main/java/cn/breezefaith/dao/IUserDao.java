package cn.breezefaith.dao;

import cn.breezefaith.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository(value="userDao")
public interface IUserDao {

    User login(@Param("username") String username, @Param("password") String password);

    int register(@Param("username") String username, @Param("password") String password,@Param("phone") String phone,@Param("email") String email) throws Exception;

    User checkUsernameAvailable(@Param("username")String username);

    User checkPhoneAvailable(@Param("phone")String phone);

    User checkEmailAvailable(@Param("email")String email);

    boolean updatePersonInfo(User user);

    User findById(@Param("uid")Integer uid);
}
