package com.project.shop.Repository;

import com.project.shop.Model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISellRepository extends JpaRepository<Sell, Long> {
}
