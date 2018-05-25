package cn.breezefaith.service;

import cn.breezefaith.entity.Item;

import java.util.List;

public interface IItemService {
    List<Item> findAll();

    List<Item> findAllInRedis();

    byte[] getImage(String key);

    Item findById(String itemId);

    int itemCount();

    String getItemList();

    boolean addToCart(String token, String itemId);

    List<Item> getCart(String token);

    boolean deleteItem(String token, String itemId);

    byte[] getImageInMySQL(String iid);

    byte[] getImageInRedis(String iid);

    byte[] getImageInURL(String url);

    boolean updateItem(String iid, String itemName, String itemCategory, String itemDescription, String itemImage, String itemPrice);

    boolean deleteItemByAdmin(String iid);

    boolean addItem(String itemName, String itemCategory, String itemDescription, String itemImage, String itemPrice);
}
