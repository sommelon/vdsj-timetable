package sk.vdsj.timetable.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Time {
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public Time(DayOfWeek day, LocalTime startTime, LocalTime endTime){
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    // Getters and Setters

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public static Time valueOf(String time) {
        if (time == null) return null;

        DayOfWeek day;
        LocalTime endTime;
        LocalTime startTime;
        try {
            day = DayOfWeek.valueOf(Days.valueOf(time.substring(0, time.indexOf(' '))).getKey());
            String startTimeString = time.substring(time.indexOf(' ') + 1, time.indexOf('-'));
            if (startTimeString.length() == 4) { // ("H:MM").length() = 4
                startTimeString = "0" + startTimeString;
            }
            startTime = LocalTime.parse(startTimeString);
            endTime = LocalTime.parse(time.substring(time.indexOf('-') + 1));
        } catch (Exception e) {
            throw new TimetableLanguageException("Invalid time format: " + time);
        }

        return new Time(day, startTime, endTime);
    }

    public void validate() {
        if (day == null) {
            throw new TimetableLanguageException("Day isn't specified.");
        } else if (startTime == null) {
            throw new TimetableLanguageException("Starting time isn't specified.");
        } else if (endTime == null) {
            throw new TimetableLanguageException("Ending time isn't specified.");
        }

        if (startTime.isAfter(endTime)) {
            throw new TimetableLanguageException("Starting time must be earlier than ending time.");
        }
    }

    @Override
    public String toString() {
        return Days.valueOf(day.toString()).getKey() + " " + startTime.toString() + "-" + endTime.toString();
    }
}
