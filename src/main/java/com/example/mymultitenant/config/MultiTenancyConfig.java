package com.example.mymultitenant.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiTenancyConfig {

    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider(MultiTenantConnectionProviderImpl multiTenantConnectionProviderImpl) {
        return multiTenantConnectionProviderImpl;
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver(CurrentTenantIdentifierResolverImpl currentTenantIdentifierResolverImpl) {
        return currentTenantIdentifierResolverImpl;
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(MultiTenantConnectionProvider multiTenantConnectionProvider,
                                                                       CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
        return hibernateProperties -> {
            hibernateProperties.put("hibernate.multiTenancy", "SCHEMA");
            hibernateProperties.put("hibernate.multi_tenant_connection_provider", multiTenantConnectionProvider);
            hibernateProperties.put("hibernate.tenant_identifier_resolver", currentTenantIdentifierResolver);
        };
    }
}
