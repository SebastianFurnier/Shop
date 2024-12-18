package com.project.shop.Service;

import com.project.shop.Model.Sell;
import com.project.shop.Model.SellItem;

import java.util.List;
import java.util.Map;

public interface ISellService {
    Sell createSell(List<SellItem> products, Long userId);
    List<Sell> getAllSellFromUser(Long userId);

    List<Sell> getAll();

    Sell cancelSell(Long sellId);

    Sell confirmSell(Long sellId);
}
