package org.Group34.controller.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import org.Group34.model.App;
import org.Group34.model.Result;
import org.Group34.model.User;


public class RegisterMenuController {
    public Result register(Scanner scanner, String username,
                           String password, String passwordConfirm,
                           String nickname, String email, String gender) {
        if (App.getUserByUsername(username) != null) {
            System.out.println("this username is already taken!");
            return new Result(false, "This username is already taken!\n");
        }
        if (!isUsernameValid(username)) {
            return new Result(false, "username format is invalid!\n");
        }
        else if (!isEmailValid(email)) {
            return new Result(false, "email format is invalid!\n");
        }
        else if (!isPasswordValid(password)) {
            return new Result(false, "password format is invalid!\n");
        }
        else if (password.length() < 8) {
            return new Result(false, "The password is weak,\nThe password must consist of at least 8 characters\n");
        }
        else if (!password.matches("(?=.*[a-z]).+")) {
            return new Result(false, "The password is weak,\nThe password must consist of lowercase characters\n");
        }
        else if (!password.matches("(?=.*[A-Z]).+")) {
            return new Result(false, "The password is weak,\nThe password must consist of uppercase characters\n");
        }
        else if (!password.matches("(?=.*\\d).+")) {
            return new Result(false, "The password is weak,\nThe password must consist of numbers\n");
        }
        else if (!password.matches("(?=.*[!@#$%^&*()_\\-+=\\[\\]{};:'\",.<>/?\\\\|`~]).+")) {
            return new Result(false, "The password is weak,\nThe password must consist of special characters\n");
        }
        else if (!password.equals(passwordConfirm)) {
            System.out.println("Re-entered password is incorrect.");
            System.out.println("Please enter the password again or back to the Register menu");
            while (true) {
                passwordConfirm = scanner.nextLine();
                if (passwordConfirm.equals(password)) {
                    break;
                }
                else if (passwordConfirm.equals("back")) {
                    return new Result(false, "Redirecting to the Register menu ...\n");
                }
                else {
                    System.out.println("Re-entered password is incorrect.");
                    System.out.println("Please enter the password again or back to the Register menu");
                }
            }
        }
        User newUser = new User(username, password, nickname, email, gender);
        App.addUser(newUser);
        App.setCurrentUser(newUser);
        return new Result(true, "");
    }

    public Result registerWithRandomPassword(Scanner scanner, String username, String nickname, String email, String gender) {
        if (App.getUserByUsername(username) != null) {
            System.out.println("this username is already taken!");
            String suggestion = generateNewUsername(username);

            System.out.println("Would you like to use this username instead? " + suggestion + " (yes/no)");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("yes")) {
                username = suggestion;
            }
            else {
                return new Result(false, "Redirecting to the Register menu ...\n");
            }
        }
        if (!isUsernameValid(username)) {
            return new Result(false, "username format is invalid!\n");
        }
        else if (!isEmailValid(email)) {
            return new Result(false, "email format is invalid!\n");
        }

        String password;
        while (true) {
            String suggestion = generateRandomPassword();
            System.out.println("Would you like to use this password? " + suggestion + " (yes/no/back)");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("yes")) {
                password = suggestion;
                break;
            }
            else if (choice.equals("back")) {
                return new Result(false, "Redirecting to the Register menu ...\n");
            }
        }

        User newUser = new User(username, password, nickname, email, gender);
        App.addUser(newUser);
        App.setCurrentUser(newUser);
        return new Result(true, "");
    }

    public Result pickQuestion(int questionNumber, String answer, String answerConfirm) {
        if (!answer.equals(answerConfirm)) {
            return new Result(false, "Please try again.\n");
        }
        User user = App.getLastUser();
        user.setSecurityQuestion(App.getSecurityQuestionByNumber(questionNumber));
        user.setSecurityAnswer(answer);
        return new Result(true, "user registered successfully.\n");
    }

    private String generateNewUsername(String username) {
        Random rand = new Random();
        String newUsername;
        do {
            int number = rand.nextInt(10000);
            newUsername = username + "-" + number;
        } while (App.getUserByUsername(newUsername) != null);
        return newUsername;
    }

    private boolean isUsernameValid(String username) {
        return username.matches("[a-zA-Z0-9-]+");
    }

    private boolean isEmailValid(String email) {
        return email.matches("(?!.*\\.\\.)([a-zA-Z0-9](?:[a-zA-Z0-9._-]*[a-zA-Z0-9])?)@([a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9-]{2,})+)");
    }

    private boolean isPasswordValid(String password) {
        return password.matches("[a-zA-Z0-9!@#$%^&*()_\\-+=\\[\\]{};:'\",.<>/?\\\\|]+");
    }

    public ArrayList<String> getSecurityQuestions() {
        return new ArrayList<>(App.getSecurityQuestions());
    }

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        Random rand = new Random();

        String LOWER = "abcdefghijklmnopqrstuvwxyz";
        String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String DIGITS = "0123456789";
        String SPECIAL = "?><,\"';:\\/|][}{+=)(*&^%$#!";

        password.append(LOWER.charAt(rand.nextInt(LOWER.length())));
        password.append(UPPER.charAt(rand.nextInt(UPPER.length())));
        password.append(DIGITS.charAt(rand.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(rand.nextInt(SPECIAL.length())));

        String allChars = LOWER + UPPER + DIGITS + SPECIAL;
        for (int i = 4; i < 10; i++) {
            password.append(allChars.charAt(rand.nextInt(allChars.length())));
        }

        ArrayList<Character> passwordChars = new ArrayList<>();
        for (char c : password.toString().toCharArray()) {
            passwordChars.add(c);
        }
        Collections.shuffle(passwordChars);

        StringBuilder finalPassword = new StringBuilder();
        for (char c : passwordChars) {
            finalPassword.append(c);
        }

        return finalPassword.toString();
    }
}
