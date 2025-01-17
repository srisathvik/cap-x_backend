package com.sathvik.portfolio_tracker.controller;

import com.sathvik.portfolio_tracker.dto.PortfolioResponseDto;
import com.sathvik.portfolio_tracker.dto.StockAddDto;
import com.sathvik.portfolio_tracker.dto.StockResponseDto;
import com.sathvik.portfolio_tracker.entity.Portfolio;
import com.sathvik.portfolio_tracker.service.Impl.IportfolioServiceImpl;
import com.sathvik.portfolio_tracker.service.IportfolioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/tracker")
@Validated
public class PortfolioController {

    private final IportfolioService portfolioService;

    public PortfolioController(IportfolioServiceImpl portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/portfolio")
    public ResponseEntity<PortfolioResponseDto> getPortfolio(@Email @RequestParam String email) {
        return ResponseEntity.ok(portfolioService.getPortfolio(email));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/stocks")
    public ResponseEntity<List<StockResponseDto>> getStocks(@Email @RequestParam String email) {
        return ResponseEntity.ok(portfolioService.getStocks(email));
    }
    
    @PostMapping("/addStock")
    public ResponseEntity<String> addStock(@Valid @RequestBody StockAddDto stock) {
        portfolioService.addStock(stock);
        return ResponseEntity.ok("Stock added successfully");
    }

    @PatchMapping("/updateStock")
    public ResponseEntity<String> updateStock(@Valid @RequestBody StockAddDto stock) {
        boolean isUpdated = portfolioService.updateStock(stock);
        if(!isUpdated)
            return ResponseEntity.badRequest().body("Stock not updated");
        return ResponseEntity.ok("Stock updated successfully");
    }

    @DeleteMapping("/deleteStock")
    public ResponseEntity<String> deleteStock(@RequestParam int userId, @RequestParam String ticker) {
        boolean isDeleted = portfolioService.deleteStock(userId,ticker);
        if(!isDeleted)
            return ResponseEntity.badRequest().body("Stock not deleted");
        return ResponseEntity.ok("Stock deleted successfully");
    }


}
