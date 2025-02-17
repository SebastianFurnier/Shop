package com.project.shop.Service;

import com.project.shop.DTO.SellDTO;
import com.project.shop.Model.Sell;
import com.project.shop.Model.SellItem;

import java.util.List;
import java.util.Map;

public interface ISellService {
    SellDTO createSell(Map<Long, Integer> products);
    List<Sell> getAllSellFromUser(Long userId);

    List<Sell> getAll();

    Sell cancelSell(Long sellId);

    Sell confirmSell(Long sellId);
}
