package main.java.org.Group34.controller.menu;

import main.java.org.Group34.model.App;
import main.java.org.Group34.model.Result;
import main.java.org.Group34.model.User;

public class ProfileMenuController {
    // change username: ---------- START ----------
    public Result changeUsername(String newUsername, User user) {
        if (isUsernameFormatValid(newUsername)) {
            return new Result(false, "Username format is invalid.");
        }
        else if (newUsername.equalsIgnoreCase(user.getUsername())) {
            return new Result(false, "This username is already your current username.");
        }
        else if (!isUsernameUnique(newUsername)) {
            return new Result(false, "Username is already taken.");
        }
        else {
            user.setUsername(newUsername);
            return new Result(true, "Username was changed successfully.");
        }
    }

    private boolean isUsernameUnique(String username) {
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username) && user != App.getCurrentUser()) {
                return false;
            }
        }
        return true;
    }

    private boolean isUsernameFormatValid(String username) {
        return username.matches("^[a-zA-Z0-9-]+$");
    }
    // change username: ---------- END ------------


    // change password: ---------- START ----------
    public Result changePassword(String newPassword, String oldPassword, User user) {
        if (!oldPassword.equals(user.getPassword())) {
            return new Result(false, "Old Password do not match.");
        }
        else if (!isPasswordFormatValid(newPassword)) {
            return new Result(false, "Password format is invalid.");
        }
        else if (newPassword.equals(oldPassword)) {
            return new Result(false, "This password is already your current password.");
        }
        else if (!isPasswordStrong(newPassword)) {
            return new Result(false, "New password is weak. Choose a strong password.");
        }
        else {
            user.setPassword(newPassword);
            return new Result(true, "Password was changed successfully.");
        }
    }

    private boolean isPasswordFormatValid(String password) {
        return password.matches("^[a-zA-Z0-9!#$%^&*()=+{}\\[\\]|\\\\/:\";',<>?]+$");
    }

    private boolean isPasswordStrong(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!#$%^&*()=+{}\\[\\]|\\\\/:\";',<>?]).{8,}$");
    }
    // change username: ---------- END ------------


    // change nickname: ---------- START ----------
    public Result changeNickname(String newNickname, User user) {
        if (newNickname.equals(user.getNickname())) {
            return new Result(false, "Nickname is already your current nickname.");
        }
        else {
            user.setNickname(newNickname);
            return new Result(true, "Nickname was changed successfully.");
        }
    }
    // change nickname: ---------- END ------------


    // change email: ---------- START ----------
    public Result changeEmail(String newEmail, User user) {
        if (!isEmailFormatValid(newEmail)) {
            return new Result(false, "Email format is invalid.");
        }
        else if (!isEmailUnique(newEmail)) {
            return new Result(false, "Email is already taken.");
        }
        else if (newEmail.equalsIgnoreCase(user.getEmail())) {
            return new Result(false, "Email is already your current email.");
        }
        else {
            user.setEmail(newEmail);
            return new Result(true, "Email was changed successfully.");
        }
    }

    private boolean isEmailFormatValid(String email) {
        return email.matches("^(?=.{1,64}@)[a-zA-Z0-9](?!.*\\.\\.)[a-zA-Z0-9._-]*[a-zA-Z0-9]@[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\\.[a-zA-Z]{2,})+$");
    }

    private boolean isEmailUnique(String email) {
        for (User user : App.getUsers()) {
            if (user.getEmail().equals(email) && user != App.getCurrentUser()) {
                return false;
            }
        }
        return true;
    }
    // change email: ---------- END ------------

    // show info: ---------- START ----------
    public Result showUserInfo(User user) {
        String info = user.getUsername() + "\n"
                + user.getNickname() + "\n"
                + user.getHighestMoney() + "\n"
                + user.getPlayedGamesCount();

        return new Result(true, info);
    }
    // show info: ---------- END ------------
}
