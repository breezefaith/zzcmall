package cn.breezefaith.service;

import cn.breezefaith.entity.Item;

import java.io.IOException;
import java.util.List;

public interface IItemService {
    List<Item> findAll();

    List<Item> findAllWithRedis();

    List<Item> findAllInRedis() throws IOException;

    byte[] getImage(String key);
}
