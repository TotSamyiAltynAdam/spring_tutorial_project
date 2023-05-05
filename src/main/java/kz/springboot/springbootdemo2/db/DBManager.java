package kz.springboot.springbootdemo2.db;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Items> items = new ArrayList<>();
    static {
        items.add(new Items(1L,"IPhone 11 Pro",450000));
        items.add(new Items(2L,"Samsung A71",200000));
        items.add(new Items(3L,"XIAOMI Redmie Note 9",120000));
        items.add(new Items(4L,"NOKIA 3110",15000));
    }
    private static Long id = 5L;
    public static ArrayList<Items> getItems(){
        return items;
    }
    public static void addItem(Items item){
        item.setId(id++);
        items.add(item);
    }
    public static Items getItem(Long id){
        for(Items it: items){
            if(it.getId().equals(id)) return it;
        }
        return null;
    }

}
