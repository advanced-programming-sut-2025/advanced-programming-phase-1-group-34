package org.Group34.database;

import org.Group34.model.MyGame;
import org.Group34.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:game.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            initializeDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize database: " + e.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private static void initializeDatabase() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA journal_mode=WAL");
            stmt.execute("PRAGMA synchronous=NORMAL");
            stmt.execute("PRAGMA busy_timeout=5000");

            String createUserTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "username TEXT PRIMARY KEY,"
                    + "password TEXT NOT NULL,"
                    + "email TEXT NOT NULL,"
                    + "nickname TEXT NOT NULL,"
                    + "gender TEXT NOT NULL,"
                    + "security_question TEXT,"
                    + "security_answer TEXT,"
                    + "avatar INTEGER DEFAULT 1,"
                    + "highest_money INTEGER DEFAULT 0,"
                    + "played_games_count INTEGER DEFAULT 0"
                    + ")";
            stmt.execute(createUserTable);

            String createGameTable = "CREATE TABLE IF NOT EXISTS games ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "creator_username TEXT NOT NULL,"
                    + "game_data BLOB,"
                    + "FOREIGN KEY (creator_username) REFERENCES users(username)"
                    + ")";
            stmt.execute(createGameTable);

            String createSecurityQuestionsTable = "CREATE TABLE IF NOT EXISTS security_questions ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "question TEXT NOT NULL UNIQUE"
                    + ")";
            stmt.execute(createSecurityQuestionsTable);

            String[] defaultQuestions = {
                    "What was the name of your elementary school?",
                    "What is the name of the city where you were born?",
                    "What was the name of your first teacher?",
                    "What is the name of your first pet?",
                    "What is your favorite movie?",
                    "In what city did your parents meet?",
                    "What was the name of the hospital where you were born?"
            };

            for (String question : defaultQuestions) {
                String insertQuestion = "INSERT OR IGNORE INTO security_questions(question) VALUES(?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertQuestion)) {
                    pstmt.setString(1, question);
                    pstmt.executeUpdate();
                }
            }
        }
    }

    public static void addUser(User user) {
        String sql = "INSERT INTO users(username, password, email, nickname, gender, security_question, security_answer, avatar, highest_money, played_games_count) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getNickname());
            pstmt.setString(5, user.getGender());
            pstmt.setString(6, user.getSecurityQuestion());
            pstmt.setString(7, user.getSecurityAnswer());
            pstmt.setInt(8, user.getAvatar());
            pstmt.setInt(9, user.getHighestMoney());
            pstmt.setInt(10, user.getPlayedGamesCount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void updateUser(User user) {
        String sql = "UPDATE users SET password = ?, email = ?, nickname = ?, gender = ?, security_question = ?, security_answer = ?, avatar = ?, highest_money = ?, played_games_count = ? WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getNickname());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getSecurityQuestion());
            pstmt.setString(6, user.getSecurityAnswer());
            pstmt.setInt(7, user.getAvatar());
            pstmt.setInt(8, user.getHighestMoney());
            pstmt.setInt(9, user.getPlayedGamesCount());
            pstmt.setString(10, user.getUsername());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveGame(MyGame game) {
        //TODO
    }

    public static MyGame loadGame(String username) {
        //TODO
        return null;
    }

    public static List<String> getSecurityQuestions() {
        List<String> questions = new ArrayList<>();
        String sql = "SELECT question FROM security_questions ORDER BY id";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                questions.add(rs.getString("question"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public static String getSecurityQuestionByNumber(int number) {
        String sql = "SELECT question FROM security_questions WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, number);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("question");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("nickname"),
                rs.getString("email"),
                rs.getString("gender")
        );
        user.setSecurityQuestion(rs.getString("security_question"));
        user.setSecurityAnswer(rs.getString("security_answer"));
        user.setAvatar(rs.getInt("avatar"));
        user.setHighestMoney(rs.getInt("highest_money"));
        user.setPlayedGamesCount(rs.getInt("played_games_count"));
        return user;
    }
}