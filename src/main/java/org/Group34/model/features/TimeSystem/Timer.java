package org.Group34.model.features.TimeSystem;

/**
 * A timer that tracks a fixed period starting from a given time
 * It can check if the given duration has passed
 *
 * You can use it for plants, trees, animals etc.
 */
public class Timer {
    private final TimeSnapshot startTime;
    private final int durationHours;

    public Timer(Time startTime, int durationHours) {
        this.startTime = startTime.snapshot();
        this.durationHours = durationHours;
    }

    public boolean isFinished(Time currentTime) {
        int passed = TimeSnapshot.hoursBetween(startTime, currentTime.snapshot());
        return passed >= durationHours;
    }
}
