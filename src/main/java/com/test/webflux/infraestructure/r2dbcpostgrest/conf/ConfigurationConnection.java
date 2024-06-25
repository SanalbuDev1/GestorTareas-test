package com.test.webflux.infraestructure.r2dbcpostgrest.conf;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class ConfigurationConnection {

    @Value("${ms-config.datasource.r2dbpostgrest.driver}")
    private String driverConfig;
    @Value("${ms-config.datasource.r2dbpostgrest.host}")
    private String urlDatabaseConfig;
    @Value("${ms-config.datasource.r2dbpostgrest.database}")
    private String databaseConfig;
    @Value("${ms-config.datasource.r2dbpostgrest.port}")
    private int puertoConfig;
    @Value("${ms-config.datasource.r2dbpostgrest.username}")
    private String usuarioConfig;
    @Value("${ms-config.datasource.r2dbpostgrest.password}")
    private String passwordConfig;

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(DRIVER, driverConfig)
                .option(HOST, urlDatabaseConfig)
                .option(PORT, puertoConfig)
                .option(USER, usuarioConfig)
                .option(PASSWORD, passwordConfig)
                .option(DATABASE, databaseConfig)
                .build();

        ConnectionFactory connectionFactory = ConnectionFactories.get(options);

        ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder(connectionFactory)
                .maxIdleTime(Duration.ofMillis(60000))
                .initialSize(5)
                .maxSize(20)
                .build();
        System.out.println(connectionFactory);
        return new ConnectionPool(poolConfiguration);
    }

}
