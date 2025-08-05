package org.Group34.model;

import org.Group34.controller.WeatherSystem;
import org.Group34.model.entities.Player;
import org.Group34.model.map.Map;
import org.Group34.database.DatabaseManager;

import java.io.*;
import java.util.HashMap;

public class MyGame {
    private User creator;
    private HashMap<User, Player> players;
    private Map map;
    private Time time;
    private WeatherSystem weatherSystem;
    private int id; // شناسه بازی در پایگاه داده

    public MyGame(User creator, HashMap<User, Player> players, Map map, Time time, WeatherSystem weatherSystem) {
        this.creator = creator;
        this.players = players;
        this.map = map;
        this.time = time;
        this.weatherSystem = weatherSystem;
    }

    public static MyGame load(String userName) {
        // بارگیری بازی از پایگاه داده
        return DatabaseManager.loadGame(userName);
    }

    public void save() {
        // ذخیره بازی در پایگاه داده
        DatabaseManager.saveGame(this);
    }

    public void delete() {
        // حذف بازی از پایگاه داده
        // پیاده‌سازی حذف بازی
        for (User user : players.keySet())
            user.setGame(null);
    }

    // متدهای getter و setter
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public HashMap<User, Player> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<User, Player> players) {
        this.players = players;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public WeatherSystem getWeatherSystem() {
        return weatherSystem;
    }

    public void setWeatherSystem(WeatherSystem weatherSystem) {
        this.weatherSystem = weatherSystem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // متدهای سریال‌سازی برای ذخیره در پایگاه داده
    public byte[] serialize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this);
        }
        return baos.toByteArray();
    }

    public static MyGame deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (MyGame) ois.readObject();
        }
    }
}