package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class UserDAO extends BaseDAO<User> {

    public UserDAO(Connection connection) {
        super(connection);
    }

    /**
     * Returns a list of users with role id
     *
     * @param roleId role id to compare to
     * @return list of users with role id
     */
    public List<User> readAllUsers(int roleId) {
        return read("SELECT * FROM user WHERE role_id = ?",
                new Object[] {roleId});
    }

    public User readUserByLogin(String username, String password) {
        List<User> results = read("SELECT * FROM user WHERE username = ? AND password = ?",
                new Object[] {username, password});
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    /**
     * Adds a user to the user db table
     *
     * @param user user to save
     */
    public void addUser(User user) {
        update("INSERT INTO user " +
                        "(role_id, given_name, family_name, username, email, password, phone) " +
                        "values (?, ?, ?, ?, ?, ?, ?)",
                new Object[] {user.getRoleId(), user.getGivenName(), user.getFamilyName(),
                        user.getUsername(), user.getEmail(), user.getPassword(), user.getPhone()});
    }

    /**
     * Updates an user in the user db table
     *
     * @param user user to update
     */
    public void updateUser(User user) {
        update("UPDATE user SET " +
                        "role_id = ?," +
                        "given_name = ?," +
                        "family_name = ?," +
                        "username = ?," +
                        "email = ?," +
                        "password = ?," +
                        "phone = ? " +
                        "WHERE id = ?",
                new Object[] {
                        user.getRoleId(),
                        user.getGivenName(),
                        user.getFamilyName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getPhone(),
                        user.getId()});
    }

    /**
     * Deletes an user from the user db table
     *
     * @param user user to delete
     */
    public void deleteUser(User user) {
        update("DELETE FROM user WHERE id = ?",
                new Object[] {user.getId()});
    }

    @Override
    public List<User> parseData(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            
            user.setId(resultSet.getInt("id"));
            user.setRoleId(resultSet.getInt("role_id"));
            user.setGivenName(resultSet.getString("given_name"));
            user.setFamilyName(resultSet.getString("family_name"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setPhone(resultSet.getString("phone"));

            users.add(user);
        }

        return users;
    }

}
