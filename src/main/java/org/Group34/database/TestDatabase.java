package org.Group34.database;

import org.Group34.model.User;

import java.util.List;

public class TestDatabase {
    public static void main(String[] args) {
        User testUser = new User("testuser", "password", "Test User", "test@example.com", "male");
        DatabaseManager.addUser(testUser);

        User retrievedUser = DatabaseManager.getUserByUsername("testuser");
        System.out.println("User retrieved: " + retrievedUser.getNickname());

        List<String> questions = DatabaseManager.getSecurityQuestions();
        System.out.println("Security questions: " + questions);
    }
}