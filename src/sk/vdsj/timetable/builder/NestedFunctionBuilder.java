package sk.vdsj.timetable.builder;

import sk.vdsj.timetable.model.*;

public class NestedFunctionBuilder {
    private static Timetable timeTable;

    public static Timetable getTimeTable() {
        return timeTable;
    }

    public static void timetable(String programme, String semester, String startDate, String endDate, String grade, Schedule... schedules) {
        timeTable = new Timetable(programme, semester, DateRange.valueOf(startDate + " - " + endDate), grade, schedules);
    }

    public static Schedule schedule(String title, Event... events) {
        return new Schedule(title, events);
    }

    public static String[] organiser(String name) {
        return new String[]{name};
    }

    public static String[] organisers(String... names) {
        return names;
    }

    // All options for event
    // With groups
    public static Event event(String type, String time, String location, String groups, String[] organisers, String note, int interval) {
        return new Event(type, Time.valueOf(time), location, groups, organisers, note, interval);
    }

    public static Event event(String type, String time, String location, String groups, String[] organisers, String note) {
        return new Event(type, Time.valueOf(time), location, groups, organisers, note, 1);
    }

    public static Event event(String type, String time, String location, String groups, String[] organisers, int interval) {
        return new Event(type, Time.valueOf(time), location, groups, organisers, null, interval);
    }

    public static Event event(String type, String time, String location, String groups, String... organisers) {
        return new Event(type, Time.valueOf(time), location, groups, organisers, null, 1);
    }

    // Without groups
    public static Event event(String type, String time, String location, String... organisers) {
        return new Event(type, Time.valueOf(time), location, null, organisers, null, 1);
    }

    public static Event event(String type, String time, String location, String[] organisers, String note, int interval) {
        return new Event(type, Time.valueOf(time), location, null, organisers, note, interval);
    }

    public static Event event(String type, String time, String location, String[] organisers, String note) {
        return new Event(type, Time.valueOf(time), location, null, organisers, note, 1);
    }

    public static Event event(String type, String time, String location, String[] organisers, int interval) {
        return new Event(type, Time.valueOf(time), location, null, organisers, null, interval);
    }
}
