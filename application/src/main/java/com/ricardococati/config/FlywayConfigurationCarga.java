package com.ricardococati.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class FlywayConfigurationCarga extends FlywayAutoConfiguration {

  private final DataSource dataSource;

  @Bean(initMethod = "migrate")
  public Flyway flywayInitializer() {
    return Flyway
        .configure()
        .dataSource(dataSource)
        .locations("/db/migration")
        .schemas("public")
        .baselineOnMigrate(true)
        .sqlMigrationPrefix("V")
        .load();
  }
}
