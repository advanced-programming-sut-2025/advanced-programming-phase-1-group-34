package org.Group34.model;

import org.Group34.controller.WeatherSystem;
import org.Group34.model.entities.Player;
import org.Group34.model.map.Map;
import org.Group34.database.DatabaseManager;

import java.io.*;
import java.util.HashMap;

public record MyGame(
        User creator,
        HashMap<User, Player> players,
        Map map,
        Time time,
        WeatherSystem weatherSystem
) implements Serializable {

    private static int id;

    // متدهای دسترسی برای فیلد اضافی
    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // متدهای کسب و کار
    public static MyGame load(String userName) {
        return DatabaseManager.loadGame(userName);
    }

    public void save() {
        DatabaseManager.saveGame(this);
    }

    public void delete() {
        for (User user : players.keySet()) {
            user.setGame(null);
        }
    }

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

    public HashMap<User, Player> players() {
        return new HashMap<>(players);
    }
}