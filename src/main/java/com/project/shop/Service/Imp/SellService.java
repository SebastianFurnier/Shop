package com.project.shop.Service.Imp;

import com.project.shop.Repository.ISellRepository;
import com.project.shop.Service.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellService implements ISellService {
    @Autowired
    private ISellRepository sellRepository;
}
