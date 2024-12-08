package com.project.shop.Repository;

import com.project.shop.Model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISellRepository extends JpaRepository<Sell, Long> {
    Sell searchSellById(Long id);
    List<Sell> searchSellByUserId(Long id);
}
