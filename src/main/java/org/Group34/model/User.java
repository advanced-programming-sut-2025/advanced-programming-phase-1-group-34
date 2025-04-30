package org.Group34.model;

/**
 * Represents a user in the game system
 * Only essential fields for Main menu and Profile menu are added
 * Make sure to complete it
 */

public class User {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String gender;

    private int highestMoney;

    private int playedGamesCount;

    public User(String username, String password, String nickname, String email, String gender) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getHighestMoney() {
        return highestMoney;
    }

    public void setHighestMoney(int highestMoney) {
        this.highestMoney = highestMoney;
    }

    public int getPlayedGamesCount() {
        return playedGamesCount;
    }

    public void setPlayedGamesCount(int playedGamesCount) {
        this.playedGamesCount = playedGamesCount;
    }

    private String getGender() {
        return gender;
    }

    private void setGender(String gender) {
        this.gender = gender;
    }
}
