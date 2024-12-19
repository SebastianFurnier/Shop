package com.project.shop.Model;

import com.project.shop.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date sellDate;
    private float finalPrice;
    private Status status;
    private Long userId;

    @OneToMany(mappedBy = "sell", cascade = CascadeType.ALL)
    private List<SellItem> itemsList;
}
