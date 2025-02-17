package com.project.shop.Service.Imp;

import com.project.shop.DTO.SellDTO;
import com.project.shop.Enum.Status;
import com.project.shop.ExceptionHandler.ResourceNotFoundException;
import com.project.shop.Model.Product;
import com.project.shop.Model.Sell;
import com.project.shop.Model.SellItem;
import com.project.shop.Repository.IProductRepository;
import com.project.shop.Repository.ISellRepository;
import com.project.shop.Repository.IUserRepository;
import com.project.shop.Service.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellService implements ISellService {
    @Autowired
    private ISellRepository sellRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProductRepository productRepository;

    float getFinalPrice(List<SellItem> products){
        float finalPrice = 0;
        for(SellItem item:products){
            finalPrice = finalPrice + item.finalPrice();
        }
        return finalPrice;
    }

    @Override
    public SellDTO createSell(Map<Long, Integer> productsIds) {

        List<Product> productsList = productRepository.findAllById(productsIds.keySet());

        Sell newSell = new Sell();

        if (productsList.size() != productsIds.size())
            throw new ResourceNotFoundException("Some Id are not exist.");

        List<SellItem> sellItems = productsList.stream()
                .map(product -> new SellItem(
                        null,
                        newSell,
                        productRepository.findById(product.getId()).get(),
                        productsIds.get(product.getId())
                ))
                .toList();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            throw new IllegalStateException("User isn't authenticated.");

        String username = (String) authentication.getPrincipal();

        Long userId = userRepository.getIdByUsername(username);

        newSell.setItemsList(sellItems);
        newSell.setUserId(userId);
        newSell.setSellDate(Date.from(Instant.now()));
        newSell.setFinalPrice(getFinalPrice(sellItems));
        newSell.setStatus(Status.Pending);

        return new SellDTO(sellRepository.save(newSell));
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
