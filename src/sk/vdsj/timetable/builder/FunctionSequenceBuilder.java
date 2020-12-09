package sk.vdsj.timetable.builder;

import sk.vdsj.timetable.model.*;

import java.util.ArrayList;
import java.util.List;

public class FunctionSequenceBuilder {
    private static Timetable timetable;
    private static Schedule scheduleContext;
    private static Event eventContext;

    private static List<Schedule> schedules = new ArrayList<>();
    private static List<Event> events;
    private static List<String> organisers;

    public static void timetable(String programme, String semester, String startDate, String endDate, String grade) {
        timetable = new Timetable(programme, semester, DateRange.valueOf(startDate + " - " + endDate), grade, null);
    }

    public static void schedule(String title) {
        addPreviousSchedule();
        eventContext = null;
        scheduleContext = new Schedule(title, null);
        events = new ArrayList<>();
    }

    public static void event(String type, String time, String location) {
        addPreviousEvent();
        eventContext = new Event(type, Time.valueOf(time), location, null, null, null, 1);
        organisers = new ArrayList<>();
    }

    public static void note(String note) {
        eventContext.setNote(note);
    }

    public static void groups(String groups) {
        eventContext.setGroups(groups);
    }

    public static void period(int period) {
        eventContext.setPeriod(period);
    }

    public static void organiser(String name) {
        organisers.add(name);
    }

    private static void addPreviousSchedule() {
        if (scheduleContext == null) {
            return;
        }

        addPreviousEvent();
        scheduleContext.setEvents(events.toArray(new Event[]{}));
        schedules.add(scheduleContext);
    }

    private static void addPreviousEvent() {
        if (eventContext == null) {
            return;
        }

        eventContext.setOrganisers(organisers.toArray(new String[]{}));
        events.add(eventContext);
    }

    public static Timetable getTimeTable() {
        addPreviousSchedule();
        timetable.setSchedules(schedules.toArray(new Schedule[]{}));
        return timetable;
    }

}
