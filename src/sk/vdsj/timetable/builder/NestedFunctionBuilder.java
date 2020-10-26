package sk.vdsj.timetable.builder;

import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Time;
import sk.vdsj.timetable.model.Timetable;

public class NestedFunctionBuilder {
    private static Timetable timeTable;

    public static Timetable getTimeTable() {
        return timeTable;
    }

    public static void TimeTable(String programme, String semester, String grade, Schedule... schedules) {
        timeTable = new Timetable(programme, semester, grade, schedules);
    }

    public static Schedule Schedule(String title, Event... events) {
        return new Schedule(title, events);
    }

    public static String[] Person(String name) {
        return new String[]{name};
    }

    public static String[] Persons(String... names) {
        return names;
    }

    // All options for event
    public static Event Event(String type, String time, String room, String groups, String[] persons, String note) {
        return new Event(type, Time.valueOf(time), room, groups, persons, note);
    }

    public static Event Event(String type, String time, String room, String groups, String... persons) {
        return new Event(type, Time.valueOf(time), room, groups, persons, null);
    }

    public static Event Event(String type, String time, String room, String[] persons, String note) {
        return new Event(type, Time.valueOf(time), room, null, persons, note);
    }

    public static Event Event(String type, String time, String room, String... persons) {
        return new Event(type, Time.valueOf(time), room, null, persons, null);
    }
}
