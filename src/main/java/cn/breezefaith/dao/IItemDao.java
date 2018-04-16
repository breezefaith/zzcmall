package cn.breezefaith.dao;

import cn.breezefaith.entity.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemDao")
public interface IItemDao {
    List<Item> findAll();

    Item findById(@Param("itemId") String itemId);

}
