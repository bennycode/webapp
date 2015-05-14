package com.welovecoding;

import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class DbTestUtil {

  private static String resetSqlTemplate = "ALTER TABLE %s ALTER COLUMN id RESTART WITH 1";

  private DbTestUtil() {
  }

  public static void resetAutoIncrementColumns(ApplicationContext applicationContext,
                                               String... tableNames) throws SQLException {
    DataSource dataSource = applicationContext.getBean(DataSource.class);
    try (Connection dbConnection = dataSource.getConnection()) {
      //Create SQL statements that reset the auto increment columns and invoke
      //the created SQL statements.
      for (String resetSqlArgument : tableNames) {
        try (Statement statement = dbConnection.createStatement()) {
          String resetSql = String.format(resetSqlTemplate, resetSqlArgument);
          statement.execute(resetSql);
        }
      }
    }
  }
}
