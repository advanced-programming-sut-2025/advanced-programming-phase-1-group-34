package org.Group34.controller.TimeSystem;

/**
 * Timer represents a duration-based tracker that checks
 * whether a specified amount of in-game hours has passed
 * since the timer started.
 */
public class Timer {
    private final TimeSnapshot startTime;
    private final int durationHours;

    public Timer(Time currentTime, int durationHours) {
        this.startTime = new TimeSnapshot(currentTime);
        this.durationHours = durationHours;
    }

    public boolean isCompleted(Time currentTime) {
        int hoursPassed = TimeSnapshot.hoursBetween(startTime, new TimeSnapshot(currentTime));
        return hoursPassed >= durationHours;
    }
}
