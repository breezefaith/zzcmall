package cn.breezefaith.dao;

import cn.breezefaith.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemDao")
public interface IItemDao {
    List<Item> findAll();
}
