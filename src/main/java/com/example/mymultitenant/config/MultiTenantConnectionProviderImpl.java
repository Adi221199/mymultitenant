package com.example.mymultitenant.config;

import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class MultiTenantConnectionProviderImpl extends AbstractMultiTenantConnectionProvider {

    @Autowired
    private DataSource dataSource;

    @Override
    public ConnectionProvider getAnyConnectionProvider() {
        return new ConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                return dataSource.getConnection();
            }

            @Override
            public void closeConnection(Connection conn) throws SQLException {
                conn.close();
            }

            @Override
            public boolean supportsAggressiveRelease() {
                return false;
            }

            @Override
            public boolean isUnwrappableAs(Class<?> unwrapType) {
                return unwrapType.isInstance(this);
            }

            @Override
            public <T> T unwrap(Class<T> unwrapType) {
                return unwrapType.cast(this);
            }
        };
    }

    @Override
    public ConnectionProvider selectConnectionProvider(Object tenantIdentifier) {
        return new ConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                Connection connection = dataSource.getConnection();
                connection.createStatement().execute("SET search_path TO " + tenantIdentifier);
                return connection;
            }

            @Override
            public void closeConnection(Connection conn) throws SQLException {
                conn.close();
            }

            @Override
            public boolean supportsAggressiveRelease() {
                return false;
            }

            @Override
            public boolean isUnwrappableAs(Class<?> unwrapType) {
                return unwrapType.isInstance(this);
            }

            @Override
            public <T> T unwrap(Class<T> unwrapType) {
                return unwrapType.cast(this);
            }
        };
    }
}
