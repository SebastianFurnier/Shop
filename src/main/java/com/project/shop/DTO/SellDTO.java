package com.project.shop.DTO;

import com.project.shop.Enum.Status;
import com.project.shop.Model.Sell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellDTO {
    private Long sellId;
    private Long userId;
    private float price;
    private Status status;

    public SellDTO(Sell sell) {
        this.sellId = sell.getId();
        this.userId = sell.getUserId();
        this.price = sell.getFinalPrice();
        this.status = sell.getStatus();
    }
}
