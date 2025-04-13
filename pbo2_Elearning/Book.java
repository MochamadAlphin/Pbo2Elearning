package com.mycompany.pbo2_Elearning;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Book extends LibraryEntity {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author, boolean isAvailable) {
        super(id);
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public String getTitle() { 
        return title; 
    }
    public String getAuthor() { 
        return author; 
    }
    public boolean isAvailable() { 
        return isAvailable; 
    }

    public static void addBookService(Book book) {
        String query = "INSERT INTO books (title, author, is_available) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setBoolean(3, book.isAvailable());
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding book:");
            e.printStackTrace();
        }
    }

    public static List<Book> getBooksService() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("is_available")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving books:");
            e.printStackTrace();
        }
        return books;
    }

    public static void deleteBookService(int id) {
        String query = "DELETE FROM books WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("No book found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting book:");
            e.printStackTrace();
        }
    }

    @Override
    public String getDetails() {
        return "Book [ID=" + getId() + ", Title=" + title + ", Author=" + author + ", Available=" + isAvailable + "]";
    }
}