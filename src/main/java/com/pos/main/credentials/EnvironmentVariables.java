package com.pos.main.credentials;

import java.util.Iterator;
import java.util.Properties;
import java.util.function.Consumer;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

public class EnvironmentVariables {

    protected final Dotenv dotenv;

    public EnvironmentVariables() {
        this.dotenv = Dotenv.load();
    }

    public Properties getEnvironmentVariables() {
        Properties environmentVars = new Properties();
        Iterator<DotenvEntry> dIterator = this.dotenv.entries().iterator();
        Consumer<DotenvEntry> printer = entry -> {
            environmentVars.put(entry.getKey(), entry.getValue());
        };
        dIterator.forEachRemaining(printer);
        return environmentVars;
    }
}
