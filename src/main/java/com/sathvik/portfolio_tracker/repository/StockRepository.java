package com.sathvik.portfolio_tracker.repository;

import com.sathvik.portfolio_tracker.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Optional<Stock> findByTicker(@Param("ticker") String ticker);
}
