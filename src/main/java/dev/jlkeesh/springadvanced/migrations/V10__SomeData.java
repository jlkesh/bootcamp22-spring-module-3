package dev.jlkeesh.springadvanced.migrations;

import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class V10__SomeData extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(connection);
        var query = "insert into tasks (name, description, label) values (?, ?, ?)";
        jdbcTemplate.update(query,
                "Integrate with Uzcard (JavaBasedMigration)",
                "Use Documentation(JavaBasedMigration)",
                "Backend(JavaBasedMigration)");
    }
}
