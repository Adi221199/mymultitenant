package com.example.mymultitenant.cotroller;

//import com.example.mymultitenant.config.TenantIdentifierResolver;
import com.example.mymultitenant.model.Stock;
import com.example.mymultitenant.service.StockService;
import com.example.mymultitenant.util.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class StockController {

    private static final Logger log = LoggerFactory.getLogger(StockController.class);
    @Autowired
    StockService stockService;

//    @Autowired
//    TenantIdentifierResolver tenantIdentifierResolver;

    @PostMapping("/createStock")
    public String createStock(@RequestBody Stock stock, @RequestParam(value = "tenantId") String tenantId){
        try {
//            tenantIdentifierResolver.setCurrentTenant(tenantId);
            TenantContext.setCurrentTenant(tenantId);
            stockService.createStock(stock);
            return "Stock Added successfully!";
        } catch (Exception exception){
            log.error("Exception in creating Stock: {}", exception.getMessage());
            return "error while adding stock";
        }
    }
}
