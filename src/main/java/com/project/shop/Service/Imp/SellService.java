package com.project.shop.Service.Imp;

import com.project.shop.Enum.Status;
import com.project.shop.Model.Sell;
import com.project.shop.Model.SellItem;
import com.project.shop.Repository.ISellRepository;
import com.project.shop.Repository.IUserRepository;
import com.project.shop.Service.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class SellService implements ISellService {
    @Autowired
    private ISellRepository sellRepository;

    @Autowired
    private IUserRepository userRepository;

    float getFinalPrice(List<SellItem> products){
        float finalPrice = 0;
        for(SellItem item:products){
            finalPrice = finalPrice + item.finalPrice();
        }

        return finalPrice;
    }

    @Override
    public Sell createSell(List<SellItem> products, Long userId) {
        Sell newSell = new Sell();

        newSell.setItemsList(products);
        newSell.setUser(userRepository.searchUserById(userId));
        newSell.setSellDate(Date.from(Instant.now()));
        newSell.setFinalPrice(getFinalPrice(products));
        newSell.setStatus(Status.Pending);

        return sellRepository.save(newSell);
    }

    @Override
    public List<Sell> getAllSellFromUser(Long userId) {
        return sellRepository.searchSellByUserId(userId);
    }

    @Override
    public List<Sell> getAll(){
        return sellRepository.getAll();
    }

    @Override
    public Sell cancelSell(Long sellId) {
        Sell sell = sellRepository.searchSellById(sellId);
        sell.setStatus(Status.Canceled);

        sellRepository.save(sell);
        return sell;
    }

    @Override
    public Sell confirmSell(Long sellId) {
        Sell sell = sellRepository.searchSellById(sellId);
        sell.setStatus(Status.Paid);

        sellRepository.save(sell);
        return sell;
    }
}
