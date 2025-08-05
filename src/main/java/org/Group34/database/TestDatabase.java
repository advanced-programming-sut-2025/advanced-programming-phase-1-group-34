package org.Group34.database;

import org.Group34.database.DatabaseManager;
import org.Group34.model.User;

import java.util.List;

public class TestDatabase {
    public static void main(String[] args) {
        // تست اضافه کردن کاربر
        User testUser = new User("testuser", "password", "Test User", "test@example.com", "male");
        DatabaseManager.addUser(testUser);

        // تست دریافت کاربر
        User retrievedUser = DatabaseManager.getUserByUsername("testuser");
        System.out.println("User retrieved: " + retrievedUser.getNickname());

        // تست دریافت سوالات امنیتی
        List<String> questions = DatabaseManager.getSecurityQuestions();
        System.out.println("Security questions: " + questions);
    }
}