package com.devcolibri.handler;

import com.devcolibri.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.*;

public class UserHandler {
    // private static final String INSERT_NEW_USER = "INSERT INTO users (nickname, password) VALUES(?,?)";
    private static final String GET_USER = "SELECT * FROM users WHERE nickname = ?";
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public UserHandler() throws IOException {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("chat-unit");
            entityManager = entityManagerFactory.createEntityManager();
            connection = DriverManager.getConnection(String.valueOf(new DatabaseUrlHandler()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String nickname, String password) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User user = new User(nickname, password);
        entityManager.persist(user);
        entityTransaction.commit();
    }

    public User getUser(String nickname) {
        User user = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, nickname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getString("nickname"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}