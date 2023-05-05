package kz.springboot.springbootdemo2.services;

import kz.springboot.springbootdemo2.entities.ShopItems;

import java.util.List;

public interface ItemService {
    ShopItems addItem(ShopItems item);
    List<ShopItems> getAllItems();
    ShopItems getItem(Long id);
    void deleteItem(ShopItems item);
    ShopItems saveItem(ShopItems item);
}
