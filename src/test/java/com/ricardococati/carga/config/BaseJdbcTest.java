package com.ricardococati.carga.config;

import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Slf4j
public abstract class BaseJdbcTest {

  @Rule
  public PreparedDbRule postgresDB;

  public BaseJdbcTest() {
    postgresDB = EmbeddedPostgresRules.preparedDatabase(
        FlywayPreparer.forClasspathLocation(
            new String[]{"classpath:db/migration"})
    );
  }

  @NotNull
  protected final NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
    PreparedDbRule preparedDbRule = this.postgresDB;
    return new NamedParameterJdbcTemplate(preparedDbRule.getTestDatabase());
  }

}
