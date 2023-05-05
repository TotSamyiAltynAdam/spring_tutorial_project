package kz.springboot.springbootdemo2.controllers;

import kz.springboot.springbootdemo2.db.DBManager;
import kz.springboot.springbootdemo2.db.Items;
import kz.springboot.springbootdemo2.entities.ShopItems;
import kz.springboot.springbootdemo2.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ItemService itemService;

    @GetMapping(value="/")
    public String index(Model model){
        List<ShopItems> items = itemService.getAllItems();
        model.addAttribute("items",items);

        return "index";
    }

    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }
//    @RequestMapping(method= RequestMethod.POST)
    @PostMapping(value="/addItem")
    public String addItem(@RequestParam(name="item_name",defaultValue = "No Item") String name,
                          @RequestParam(name="item_price",defaultValue = "0") int price,
                          @RequestParam(name="item_amount",defaultValue = "0") int amount){
        itemService.addItem(new ShopItems(null,name,price,amount));
        return "redirect:/";
    }

    @GetMapping(value="/details/{idshka}")
    public String details(Model model,@PathVariable(name="idshka") Long id){
        ShopItems item = itemService.getItem(id);
        model.addAttribute("item",item);
        return "details";
    }
    @PostMapping(value = "/saveItem")
    public String saveItem(@RequestParam(name="id",defaultValue = "0") Long id,
                           @RequestParam(name="item_name",defaultValue = "No Item") String name,
                           @RequestParam(name="item_price",defaultValue = "0") int price,
                           @RequestParam(name="item_amount",defaultValue = "0") int amount){
        ShopItems item = itemService.getItem(id);
        if(item!=null){
            item.setName(name);
            item.setAmount(amount);
            item.setPrice(price);
            itemService.saveItem(item);
        }
        return "redirect:/";
    }
    @PostMapping(value = "/deleteItem")
    public String saveItem(@RequestParam(name="id",defaultValue = "0") Long id){
        ShopItems item = itemService.getItem(id);
        if(item!=null){
            itemService.deleteItem(item);
        }
        return "redirect:/";
    }
}
