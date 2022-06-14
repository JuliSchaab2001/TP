package TP.LAB5.demo.controller;


import TP.LAB5.demo.domain.Shop;
import TP.LAB5.demo.services.ShopService;
import TP.LAB5.demo.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/")
    public ResponseEntity addShop(@RequestBody Shop shop){
        return shopService.addShop(shop);
    }

    @GetMapping("/")
    public ResponseEntity<List<Shop>> getAll(){
        return shopService.getAll();
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<Shop> getById(@PathVariable Integer shopId){
        return shopService.getById(shopId);
    }


}
