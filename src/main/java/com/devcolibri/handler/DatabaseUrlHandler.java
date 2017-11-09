package com.devcolibri.handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseUrlHandler {
    private static final String URL_TO_PROPERTIES = "src\\main\\java\\com\\devcolibri\\resourses\\database.properties";
    private FileInputStream fileInputStream;
    private Properties properties;
    private String jdbc;
    private String databaseType;
    private String url;
    private String port;
    private String databaseName;
    private String username;
    private String password;

    public DatabaseUrlHandler() {
        try {
            fileInputStream = new FileInputStream(URL_TO_PROPERTIES);
            properties = new Properties();
            properties.load(fileInputStream);
            jdbc = properties.getProperty("jdbc");
            databaseType = properties.getProperty("databaseType");
            url = properties.getProperty("url");
            port = properties.getProperty("port");
            databaseName = properties.getProperty("databaseName");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return jdbc + ":" + databaseType + "://" + url + ":" + port + "/"
                + databaseName + "?user=" + username + "&password=" + password;
    }
}
