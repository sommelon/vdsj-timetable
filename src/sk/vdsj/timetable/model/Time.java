package sk.vdsj.timetable.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Time {
    private DayOfWeek day;
    private LocalTime time_from;
    private LocalTime time_to;

    public Time() {
    }

    public DayOfWeek getDay() {
        return day;
    }

    // Getters and Setters

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getTime_from() {
        return time_from;
    }

    public void setTime_from(LocalTime time_from) {
        this.time_from = time_from;
    }

    public LocalTime getTime_to() {
        return time_to;
    }

    public void setTime_to(LocalTime time_to) {
        this.time_to = time_to;
    }
}
