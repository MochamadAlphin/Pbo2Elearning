package com.mycompany.pbo2_Elearning;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Add User");
            System.out.println("7. View Users");
            System.out.println("8. Update User");
            System.out.println("9. Delete User");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    Book.addBookService(new Book(0, title, author, true));
                    break;
                case 2:
                    List<Book> books = Book.getBooksService();
                    if (books.isEmpty()) {
                        System.out.println("No books available.");
                    } else {
                        for (Book book : books) {
                            System.out.println(book.getDetails());
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter book ID to delete: ");
                    try {
                        int bookId = Integer.parseInt(scanner.nextLine());
                        Book.deleteBookService(bookId);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format!");
                    }
                    break;
                case 6:
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user email: ");
                    String email = scanner.nextLine();
                    User.addUserService(new User(0, name, email));
                    break;
                case 7:
                    List<User> users = User.getUsersService();
                    if (users.isEmpty()) {
                        System.out.println("No users available.");
                    } else {
                        for (User user : users) {
                            System.out.println(user.getDetails());
                        }
                    }
                    break;
                case 8:
                    System.out.print("Enter user ID to update: ");
                    try {
                        int userId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        User.updateUserService(userId, newName, newEmail);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format!");
                    }
                    break;
                case 9:
                    System.out.print("Enter user ID to delete: ");
                    try {
                        int userId = Integer.parseInt(scanner.nextLine());
                        User.deleteUserService(userId);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format!");
                    }
                    break;
                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
