package com.mycompany.pbo2_Elearning;

import java.util.List;

public interface UserOperations {
    void addUser(User user);
    void updateUser(int id, String newName, String newEmail);
    void deleteUser(int id);
    List<User> getUsers();
}