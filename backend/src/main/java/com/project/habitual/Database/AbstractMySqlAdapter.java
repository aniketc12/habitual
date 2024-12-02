package com.project.habitual.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
abstract class AbstractMySqlAdapter {
  private Connection connection;
  public AbstractMySqlAdapter() {
    String jdbcUrl = String.format("jdbc:mysql://%s:3306/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_DATABASE"));
    String username = System.getenv("MYSQL_USER");
    String password = System.getenv("MYSQL_PASSWORD");
    try {
      this.connection = DriverManager.getConnection(jdbcUrl, username, password);
      System.out.println("Connection successful!!");
    } catch (SQLException e) {
      this.connection = null;
      e.printStackTrace();
      System.out.println("Connection failed.");
    }
  }

  protected String insert(String table, String[] values) {
    String joinedValues = "";
    for (int i = 0; i < values.length; i++) {
      joinedValues += "'" + values[i] +"'";
      if ( i < values.length - 1) {
        joinedValues += ",";
      }
    }
    String sqlQuery = String.format("INSERT INTO %s values (%s)", table, joinedValues);

    try {
      Statement statement = this.connection.createStatement();
      int rowsAffected = statement.executeUpdate(sqlQuery);
      statement.close();
      if (rowsAffected > 0) {
        return "OK";
      }
      return "Unexpected Error";
    } catch (SQLException e) {
      return e.getLocalizedMessage();
    }
  }
}
