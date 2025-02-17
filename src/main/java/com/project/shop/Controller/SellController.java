package com.project.shop.Controller;

import com.project.shop.DTO.SellDTO;
import com.project.shop.Model.Sell;
import com.project.shop.Model.SellItem;
import com.project.shop.Service.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sells")
public class SellController {

    @Autowired
    private ISellService sellService;

    @PostMapping("/create")
    public ResponseEntity<SellDTO> createSell(@RequestBody Map<Long, Integer> itemList) {

        SellDTO sell = sellService.createSell(itemList);

        URI location = URI.create(String.format("/sells/get/%d", sell.getSellId()));

        return ResponseEntity.created(location).body(sell);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Sell>> getAllSells() {
        return ResponseEntity.ok(sellService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<Sell>> getAllSellByUser(@PathVariable Long id) {
        return ResponseEntity.ok(sellService.getAllSellFromUser(id));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Sell> cancelSell(@PathVariable Long id) {
        return ResponseEntity.ok(sellService.cancelSell(id));
    }

    @PostMapping("confirm/{id}")
    public ResponseEntity<Sell> confirmSell(@PathVariable Long id){
        return ResponseEntity.ok(sellService.confirmSell(id));
    }
}
