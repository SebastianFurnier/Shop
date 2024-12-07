package com.project.shop.Model;

import com.project.shop.Service.IProductService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SellItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sell_id")
    private Sell sell;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product item;

    private int quantity;

    public float finalPrice(){
        return item.getPrice() * quantity;
    }
}
