package org.Group34.model.entities.npcs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private Map<DayOfWeek, LocalTime> dailyRoutine = new HashMap<>();

    public void setRoutine(DayOfWeek day, LocalTime time) {
        dailyRoutine.put(day, time);
    }

    public LocalTime getRoutine(DayOfWeek day) {
        return dailyRoutine.get(day);
    }

    public Map<DayOfWeek, LocalTime> getFullSchedule() {
        return dailyRoutine;
    }
}
