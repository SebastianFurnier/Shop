package com.project.shop.Model;

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
    private String status;
    @ManyToOne
    @JoinColumn(name = "userd_id")
    private User user;

    @OneToMany(mappedBy = "sell", cascade = CascadeType.ALL)
    private List<SellItem> itemsList;
}
