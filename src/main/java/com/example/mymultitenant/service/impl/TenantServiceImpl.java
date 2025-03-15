package com.example.mymultitenant.service.impl;

import com.example.mymultitenant.model.Tenant;
import com.example.mymultitenant.repository.TenantRepository;
import com.example.mymultitenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    TenantSchemaService tenantSchemaService;

    public void createTenant(Tenant tenant) {
        tenant.setCreate_date(new Date());
        String schemaName = tenant.getTenantName().toLowerCase() + "_" + tenant.getTenantId();
        tenant.setSchemaName(schemaName);
        tenantRepository.save(tenant);
        tenantSchemaService.createSchemaAndTables(tenant.getTenantName().toLowerCase(), schemaName);
    }
}
