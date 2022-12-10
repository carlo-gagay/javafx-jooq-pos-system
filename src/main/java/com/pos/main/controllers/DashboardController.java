package com.pos.main.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.Properties;
import java.util.function.Consumer;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javafx.fxml.FXML;
import com.pos.main.App;
import com.pos.main.api.entities.tables.tables.Author;
import com.pos.main.credentials.EnvironmentVariables;

public class DashboardController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("fxml/pages/login");
        App.setCSS("assets/css/login");
    }

    @FXML
    private void testDatabaseConn() throws IOException {
        EnvironmentVariables envs = new EnvironmentVariables();
        Properties envProperties = envs.getEnvironmentVariables();

        try(
            Connection conn = DriverManager.getConnection(
                envProperties.getProperty("DATABASE_URL"), 
                envProperties.getProperty("DATABASE_USER"), 
                envProperties.getProperty("DATABASE_PASSWORD"))
            ) {

            DSLContext ctx = DSL.using(conn, SQLDialect.MYSQL);

            Result<Record> result = ctx.select().from(Author.AUTHOR).fetch();
            Iterator<Record> resultIterator = result.iterator();

            Consumer<Record> printer = record -> System.out.println(record.getValue(Author.AUTHOR.FIRST_NAME));
            resultIterator.forEachRemaining(printer);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}