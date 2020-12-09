package sk.vdsj.timetable.builder;

import sk.vdsj.timetable.builder.types.AfterEvent;
import sk.vdsj.timetable.builder.types.AfterOrganiser;
import sk.vdsj.timetable.builder.types.AfterSchedule;
import sk.vdsj.timetable.builder.types.AfterTimetable;
import sk.vdsj.timetable.model.*;

import java.util.ArrayList;
import java.util.List;

public class MethodChainingBuilder implements AfterTimetable, AfterSchedule, AfterEvent, AfterOrganiser {
    private static Timetable timetable;
    private static Schedule scheduleContext;
    private static Event eventContext;

    private static List<Schedule> schedules = new ArrayList<>();
    private static List<Event> events;
    private static List<String> organisers;

    public static AfterTimetable timetable(String programme, String semester, String startDate, String endDate, String grade) {
        timetable = new Timetable(programme, semester, DateRange.valueOf(startDate + " - " + endDate), grade, null);
        return new MethodChainingBuilder();
    }

    @Override
    public AfterSchedule schedule(String title) {
        return schedule(title, 1);
    }

    @Override
    public AfterSchedule schedule(String title, int period) {
        addPreviousSchedule();
        eventContext = null;
        scheduleContext = new Schedule(title, period, null);
        events = new ArrayList<>();

        return this;
    }

    @Override
    public AfterEvent event(String type, String time, String location) {
        return event(type, time, location, null);
    }

    @Override
    public AfterEvent event(String type, String time, String location, String groups) {
        return event(type, time, location, groups, null);
    }

    @Override
    public AfterEvent event(String type, String time, String location, String groups, String note) {
        addPreviousEvent();
        eventContext = new Event(type, Time.valueOf(time), location, groups, null, note);
        organisers = new ArrayList<>();

        return this;
    }

    @Override
    public AfterOrganiser organiser(String name) {
        organisers.add(name);
        return this;
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
