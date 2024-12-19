package com.project.shop.Controller;

import com.project.shop.Model.Sell;
import com.project.shop.Model.SellItem;
import com.project.shop.Service.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sell")
@PreAuthorize("denyAll()")
public class SellController {

    //Configurar @PreAuthorize en cada metodo

    @Autowired
    private ISellService sellService;

    @PostMapping("/create")
    public Sell createSell(@RequestBody List<SellItem> itemList, @RequestBody Long id) {
        return  sellService.createSell(itemList, id);
    }

    @GetMapping("/getall")
    public List<Sell> getAllSells() {
        return sellService.getAll();
    }

    @GetMapping("/getsells/{id}")
    public List<Sell> getAllSellByUser(@PathVariable Long id) {
        return sellService.getAllSellFromUser(id);
    }

    @PutMapping("/cancel/{id}")
    public Sell cancelSell(@PathVariable Long id) {
        return sellService.cancelSell(id);
    }

    @PostMapping("confirm/{id}")
    public Sell confirmSell(@PathVariable Long id){
        return sellService.confirmSell(id);
    }
}
