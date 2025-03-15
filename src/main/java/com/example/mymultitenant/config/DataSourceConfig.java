//package com.example.mymultitenant.config;
//
//import jakarta.persistence.Access;
//import org.hibernate.cfg.AvailableSettings;
//import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Map;
//
//@Component
//public class DataSourceConfig implements MultiTenantConnectionProvider, HibernatePropertiesCustomizer {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    public Connection getAnyConnection() throws SQLException {
//        return getConnection("PUBLIC");
//    }
//
//    @Override
//    public void releaseAnyConnection(Connection connection) throws SQLException {
//        connection.close();
//    }
//
//    @Override
//    public Connection getConnection(Object schema) throws SQLException {
//        Connection connection = dataSource.getConnection();
//        connection.setSchema((String) schema);
//        return connection;
//    }
//
//    @Override
//    public void releaseConnection(Object s, Connection connection) throws SQLException {
//        connection.setSchema("PUBLIC");
//        connection.close();
//    }
//
//    @Override
//    public boolean supportsAggressiveRelease() {
//        return false;
//    }
//
//    @Override
//    public void customize(Map<String, Object> hibernateProperties) {
//        hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
//    }
//
//    @Override
//    public boolean isUnwrappableAs(Class<?> aClass) {
//        return false;
//    }
//
//    @Override
//    public <T> T unwrap(Class<T> aClass) {
//        return null;
//    }
//}
