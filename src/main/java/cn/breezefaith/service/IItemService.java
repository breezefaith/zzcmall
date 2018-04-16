package cn.breezefaith.service;

import cn.breezefaith.entity.Item;

import java.util.List;

public interface IItemService {
    List<Item> findAll();

    List<Item> findAllWithRedis();

    List<Item> findAllInRedis();

    byte[] getImage(String key);

    Item findById(String itemId);

    String getItemList();

    boolean addToCart(String itemId);
}
