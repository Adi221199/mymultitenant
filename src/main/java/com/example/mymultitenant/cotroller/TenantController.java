package com.example.mymultitenant.cotroller;

import com.example.mymultitenant.model.Tenant;
import com.example.mymultitenant.service.TenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TenantController {

    private static final Logger log = LoggerFactory.getLogger(TenantController.class);
    @Autowired
    TenantService tenantService;

    @PostMapping("createTenant")
    public String createTenant(@RequestBody Tenant tenant){
        try {
            tenantService.createTenant(tenant);
            return "Tenant Created !";
        } catch (Exception exception){
            log.error("Exception while creating Tenant : {}", exception.getMessage());
            return "Tenant Creation Failed!";
        }
    }
}
