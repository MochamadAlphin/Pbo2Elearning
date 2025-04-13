package com.mycompany.pbo2_Elearning;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User extends LibraryEntity implements UserOperations {
    private String name;
    private String email;

    public User(int id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public String getName() { 
        return name; 
    }

    public String getEmail() { 
        return email; 
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding user:");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving users:");
            e.printStackTrace();
        }
        return users;
    }

   @Override
public void updateUser(int id, String newName, String newEmail) {
    String query = "UPDATE users SET name = ?, email = ? WHERE id = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, newName);
        stmt.setString(2, newEmail);
        stmt.setInt(3, id);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("User updated successfully!");
        } else {
            System.out.println("No user found with ID: " + id);
        }
    } catch (SQLException e) {
        System.err.println("Error updating user:");
        e.printStackTrace();
    }
}

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("No user found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting user:");
            e.printStackTrace();
        }
    }

    @Override
    public String getDetails() {
        return "User [ID=" + getId() + ", Name=" + name + ", Email=" + email + "]";
    }

    public static void addUserService(User user) {
        new User(0, user.getName(), user.getEmail()).addUser(user);
    }

    public static List<User> getUsersService() {
        User tempUser = new User(0, "", "");
        return tempUser.getUsers();
    }

    public static void updateUserService(int id, String newName, String newEmail) {
        new User(id, newName, newEmail).updateUser(id, newName, newEmail);
    }

    public static void deleteUserService(int id) {
        new User(id, "", "").deleteUser(id);
    }
}