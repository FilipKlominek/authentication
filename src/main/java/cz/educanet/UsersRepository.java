package cz.educanet;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UsersRepository implements Serializable {
    public List<User> getUsers() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/users?user=root&password=");

        ArrayList<User> users = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT u.userId, u.fullName, u.email, u.hashedPassword, u.createdAt, u.updatedAt" +
                        " FROM user AS u"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    LocalDate.parse(resultSet.getString(5)),
                    LocalDate.parse(resultSet.getString(6))
            ));
        }

        connection.close();

        return users;
    }

    public void addUser(String name, String email, String unHashedPassword) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/users?user=root&password=");

        User user = new User(name, email, unHashedPassword); //password gets hashed in constructor

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users.user (fullName, Email, hashedPassword, createdAt, updatedAt)" +
                        "VALUES (?, ?, ?, ?, ?)"
        );

        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getHashedPassword());
        preparedStatement.setString(4, user.getCreatedAt().toString());
        preparedStatement.setString(5, user.getUpdatedAt().toString());

        preparedStatement.execute();

        connection.close();
    }
}
