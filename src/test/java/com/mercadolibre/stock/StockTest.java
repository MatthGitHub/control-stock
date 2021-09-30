package com.mercadolibre.stock;

import com.mercadolibre.stock.exception.CustomException;
import com.mercadolibre.stock.model.entity.Deposit;
import com.mercadolibre.stock.model.entity.Location;
import com.mercadolibre.stock.model.entity.Stock;
import com.mercadolibre.stock.service.StockService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class StockTest {
    @Autowired
    private StockService stockService;
    private Stock stock;
    private Deposit deposit;
    private Location location;

    @BeforeAll
    public void doBefore() {
        this.deposit = this.buildDeposit();
        this.location = this.buildLocation();
        this.stock = this.buildStock();
    }

    @Test
    @Order(1)
    public void testSaveStock() {
        Stock stock = this.stockService.save(this.stock);
        assertNotNull(stock);
        assertNotNull(stock.getId());
    }

    @Test
    @Order(2)
    public void testSaveExistent() {
        assertThrows(CustomException.class, () -> this.stockService.save(buildStock()));
    }

    @Test
    @Order(3)
    public void doAfterAll() {
        this.stockService.removeForTestOnly(stock.getId());
    }

    private Deposit buildDeposit(){
        return new Deposit("AR99");
    }

    private Location buildLocation(){
        return new Location("AA-00-00-DE", this.deposit);
    }

    private Stock buildStock(){
        return new Stock("MLA813727183", this.location);
    }
}
