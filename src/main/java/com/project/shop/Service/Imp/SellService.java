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
import java.util.Optional;

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
        newSell.setUserId(userId);
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
        return sellRepository.findAll();
    }

    @Override
    public Sell cancelSell(Long sellId) {
        Optional<Sell> sell = sellRepository.findById(sellId);
        Sell sellAux = sell.get();
        sellAux.setStatus(Status.Canceled);

        sellRepository.save(sellAux);
        return sellAux;
    }

    @Override
    public Sell confirmSell(Long sellId) {
        Optional<Sell> sell = sellRepository.findById(sellId);
        Sell sellAux = sell.get();

        sellAux.setStatus(Status.Paid);

        sellRepository.save(sellAux);
        return sellAux;
    }
}
