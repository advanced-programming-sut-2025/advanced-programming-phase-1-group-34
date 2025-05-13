package org.Group34.model.entities.naturalElements;

import org.Group34.model.entities.Entity;
import org.Group34.model.enums.Season;

import java.util.ArrayList;

public class ForagingTree implements Entity, Foraging {
    private String name;
    private ArrayList<Season> seasons;

    public ForagingTree(String name, String[] seasons) {
        this.name = name;
        for (String season : seasons) {
            if (season.equals("Spring")) {
                this.seasons.add(Season.SPRING);
            } else if (season.equals("Summer")) {
                this.seasons.add(Season.SUMMER);
            } else if (season.equals("Autumn")) {
                this.seasons.add(Season.FALL);
            } else if (season.equals("Winter")) {
                this.seasons.add(Season.WINTER);
            }
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public String getInformation() {
        StringBuilder result = new StringBuilder();

        result
                .append("Name: " + name + "\n")
                .append("Type: " + "Foraging Tree" + "\n")
                .append("Season: ");

        for (Season season : seasons) {
            result.append(season.getName() + ", ");
        }
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        result.append("\n");

        return result.toString();
    }
}
