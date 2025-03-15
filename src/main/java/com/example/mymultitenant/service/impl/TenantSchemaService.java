package com.example.mymultitenant.service.impl;

import com.example.mymultitenant.repository.TenantRepository;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Locale;

@Service
public class TenantSchemaService {

    private static final Logger log = LoggerFactory.getLogger(TenantSchemaService.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TenantRepository tenantRepository;

    public void createSchemaAndTables(){
        tenantRepository.findAll().forEach(tenant -> {
            try {
                String sql = "CREATE SCHEMA IF NOT EXISTS " + tenant.getSchemaName();
                jdbcTemplate.execute(sql);
                createLiquibase(tenant.getSchemaName().toLowerCase());
            } catch (Exception exception){
                log.error("Error while creating schema for tenant {}", tenant.getTenantName(), exception.getMessage());
            }
        });
    }

    public void createSchemaAndTables(String tenantName, String schemaName){
        try {
            String sql = "CREATE SCHEMA IF NOT EXISTS " + schemaName;
            jdbcTemplate.execute(sql);
            createLiquibase(schemaName.toLowerCase());
        } catch (Exception exception){
            log.error("Error while creating schema for tenant {}", tenantName, exception.getMessage());
        }
    }

    private void createLiquibase(String schemaName){
        try(Connection connection = jdbcTemplate.getDataSource().getConnection();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection)))
        {
            database.setDefaultSchemaName(schemaName);
            new Liquibase("db/changelog/db.changelog-master.yaml", new ClassLoaderResourceAccessor(), database).update("");
        } catch (Exception exception){
            log.error("Error while creating Tables for schema {}", schemaName, exception.getMessage());
        }
    }
}
