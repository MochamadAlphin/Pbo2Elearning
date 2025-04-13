package com.mycompany.pbo2_Elearning;

public interface LibraryOperations {
    void addBook(Book book);
    void viewBooks();
    void borrowBook(int bookId, int userId);
    void returnBook(int bookId);
}