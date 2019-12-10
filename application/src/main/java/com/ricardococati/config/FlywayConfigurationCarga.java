package com.ricardococati.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FlywayConfigurationCarga extends FlywayAutoConfiguration {

  private final DataSource dataSource;

  @Bean(initMethod = "migrate")
  public Flyway flywayInitializer() {
    Flyway flyway = new Flyway();
    flyway.setDataSource(dataSource);
    flyway.setLocations("/db/migration");
    flyway.setSchemas("public");
    flyway.setBaselineOnMigrate(true);
    flyway.setSqlMigrationPrefix("V");
    flyway.migrate();
    return flyway;
  }
}
