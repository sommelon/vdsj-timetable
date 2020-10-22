package sk.vdsj.timetable.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Time {
    private DayOfWeek day;
    private LocalTime time_from;
    private LocalTime time_to;

    public Time() {
    }

    public Time(DayOfWeek day, LocalTime time_from, LocalTime time_to){
        this.day = day;
        this.time_from = time_from;
        this.time_to = time_to;
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

    public void validate() {
        if (day == null || time_from == null || time_to == null) {
            throw new TimetableLanguageException("Missing required parameters in a Time object");
        }

        if (time_from.isAfter(time_to)) {
            throw new TimetableLanguageException("Starting time must be earlier than ending time");
        }
    }

    @Override
    public String toString() {
        return Days.valueOf(day.toString()).getKey() + " " + time_from.toString() + "-" + time_to.toString();
    }
}
