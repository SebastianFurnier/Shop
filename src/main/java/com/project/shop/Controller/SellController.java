package com.project.shop.Controller;

import com.project.shop.Service.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellController {
    @Autowired
    private ISellService sellService;
}
