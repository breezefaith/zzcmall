package cn.breezefaith.dao;

import cn.breezefaith.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("addressDao")
public interface IAddressDao {
    Address findByAid(@Param("aid") Integer aid);
    int deleteByAid(@Param("aid")Integer aid);
    List<Address> findByUid(@Param("uid")Integer uid);
    boolean addAddress(@Param("uid") Integer uid, @Param("name") String name, @Param("phone") String phone, @Param("post_code") String postCode, @Param("address") String address);
}
