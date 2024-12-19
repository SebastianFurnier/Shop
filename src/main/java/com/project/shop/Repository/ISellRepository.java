package com.project.shop.Repository;

import com.project.shop.Model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISellRepository extends JpaRepository<Sell, Long> {

    @Query("SELECT s FROM Sell s WHERE s.userId = :id")
    List<Sell> searchSellByUserId(@Param("id") Long id);

}
