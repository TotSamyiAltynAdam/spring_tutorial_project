package kz.springboot.springbootdemo2.controllers;

import kz.springboot.springbootdemo2.db.DBManager;
import kz.springboot.springbootdemo2.db.Items;
import kz.springboot.springbootdemo2.entities.Categories;
import kz.springboot.springbootdemo2.entities.Countries;
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

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries",countries);

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
                          @RequestParam(name="item_amount",defaultValue = "0") int amount,
                          @RequestParam(name="country_id",defaultValue = "0") Long id){
        Countries country = itemService.getCountry(id);
        if(country != null) {

            ShopItems shopItem = new ShopItems();
            shopItem.setName(name);
            shopItem.setPrice(price);
            shopItem.setAmount(amount);
            shopItem.setCountry(country);

            itemService.addItem(shopItem);
        }
        return "redirect:/";
    }

    @GetMapping(value="/details/{idshka}")
    public String details(Model model,@PathVariable(name="idshka") Long id){
        ShopItems item = itemService.getItem(id);
        model.addAttribute("item",item);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries",countries);

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories",categories);

        return "details";
    }
    @PostMapping(value = "/saveItem")
    public String saveItem(@RequestParam(name="id",defaultValue = "0") Long id,
                           @RequestParam(name="country_id",defaultValue = "0") Long countryId,
                           @RequestParam(name="item_name",defaultValue = "No Item") String name,
                           @RequestParam(name="item_price",defaultValue = "0") int price,
                           @RequestParam(name="item_amount",defaultValue = "0") int amount){
        ShopItems item = itemService.getItem(id);
        if(item!=null){
            Countries cnt = itemService.getCountry(countryId);
            if(cnt!=null) {
                item.setName(name);
                item.setAmount(amount);
                item.setPrice(price);
                item.setCountry(cnt);
                itemService.saveItem(item);
            }
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

    @PostMapping(value = "/assignCategory")
    public String assignCategory(@RequestParam(name="item_id") Long itemId,
                                 @RequestParam(name="category_id") Long categoryId){
        Categories cat = itemService.getCategory(categoryId);
        if(cat!=null){
            ShopItems item = itemService.getItem(itemId);
            if(item!=null){
                List<Categories> categories = item.getCategories();
                if(categories==null){
                    categories = new ArrayList<>();
                }
                boolean found = false;

                for(Categories categories1: item.getCategories()){
                    if(categories1.getId().equals(categoryId)) {
                        found = true;
                        break;
                    }
                }

                if(!found) categories.add(cat);

                itemService.saveItem(item);
                return "redirect:/details/"+itemId;
            }
        }
        return "redirect:/";
    }
    @PostMapping(value = "/reAssignCategory")
    public String reAssignCategory(@RequestParam(name="item_id") Long itemId,
                                   @RequestParam(name="category_id") Long categoryId){
        Categories category = itemService.getCategory(categoryId);
        if(category!=null){
            ShopItems item = itemService.getItem(itemId);
            if(item!=null){
                List<Categories> categories = item.getCategories();
                for(Categories cat : categories){
                    if(cat.getId().equals(categoryId)) {
                        categories.remove(cat);
                        break;
                    }
                }
                itemService.saveItem(item);
                return "redirect:/details/"+itemId;
            }
        }
        return "redirect:/";
    }
}
