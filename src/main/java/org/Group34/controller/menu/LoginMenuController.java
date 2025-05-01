package org.Group34.controller.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import org.Group34.model.App;
import org.Group34.model.Result;
import org.Group34.model.User;

public class LoginMenuController {
    public Result login(String username, String password) {
        if (App.getUserByUsername(username) == null) {
            return new Result(false, "username doesn't exist!");
        } else if (!App.getUserByUsername(username).getPassword().equals(password)) {
            return new Result(false, "password is incorrect!");
        }
        App.setCurrentUser(App.getUserByUsername(username));
        return new Result(true, "You logged in successfully.");
    }
    public Result loginWithSave(String username, String password) {
        if (App.getUserByUsername(username) == null) {
            return new Result(false, "username doesn't exist!");
        } else if (!App.getUserByUsername(username).getPassword().equals(password)) {
            return new Result(false, "password is incorrect!");
        }
        App.setCurrentUser(App.getUserByUsername(username));
        saveTheUser(App.getCurrentUser());
        return new Result(true, "You logged in successfully.");
    }
    public Result forgetPassword(Scanner scanner, String username) {
        if (App.getUserByUsername(username) == null) {
            return new Result(false, "username doesn't exist!");
        }
        getAnswerOfQuestion(scanner, App.getUserByUsername(username));
        return new Result(true, "");
    }


    private void saveTheUser(User user) {
        // To Do
    }
    private void getAnswerOfQuestion(Scanner scanner, User user) {
        System.out.println("Please answer this question");
        System.out.println(user.getSecurityQuestion());
        String inputAnswer = scanner.nextLine();
        String password;

        if (!inputAnswer.equals(user.getSecurityAnswer())) {
            System.out.println("The answer is incorrect. Redirecting to the Login menu ...");
            return;
        } else {
            while (true) {
                System.out.println("Please enter the new password or back to Login menu");
                password = scanner.nextLine();
                if (password.equals("back")) {
                    System.out.println("Redirecting to the Login menu ...");
                    break;
                } else if (password.equals("random")) {
                    password = generateRandomPassword();
                    System.out.println("your new password is " + password);
                    System.out.println("Please enter the new password");
                    String inputPassword = scanner.nextLine();
                    if (inputPassword.equals(password)) {
                        user.setPassword(password);
                    } else {
                        System.out.println("password is incorrect. Redirecting to the Login menu ...");
                    }
                    break;
                }
                else if (!isPasswordValid(password)) {
                    System.out.println("password format is invalid!");
                } else if (password.length() < 8) {
                    System.out.println("The password is weak,\n" + "The password must consist of at least 8 characters");
                } else if (!password.matches("(?=.*[a-z]).+")) {
                    System.out.println("The password is weak,\n" + "The password must consist of lowercase characters");
                } else if (!password.matches("(?=.*[A-Z]).+")) {
                    System.out.println("The password is weak,\n" + "The password must consist of uppercase characters");
                } else if (!password.matches("(?=.*\\d).+")) {
                    System.out.println("The password is weak,\n" + "The password must consist of numbers");
                } else if (!password.matches("(?=.*[!@#$%^&*()_\\-+=\\[\\]{};:'\",.<>/?\\\\|`~]).+")) {
                    System.out.println("The password is weak,\n" + "The password must consist of special characters");
                } else {
                    System.out.println("your new password is " + password);
                    System.out.println("Please enter the new password");
                    String inputPassword = scanner.nextLine();
                    if (inputPassword.equals(password)) {
                        user.setPassword(password);
                    } else {
                        System.out.println("password is incorrect. Redirecting to the Login menu ...");
                    }
                    break;
                }
            }
        }

    }
    private boolean isPasswordValid(String password) {
        return password.matches("[a-zA-Z0-9!@#$%^&*()_\\-+=\\[\\]{};:'\",.<>/?\\\\|]+");
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
