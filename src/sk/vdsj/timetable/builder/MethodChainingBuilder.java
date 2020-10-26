package sk.vdsj.timetable.builder;

import sk.vdsj.timetable.builder.types.AfterEvent;
import sk.vdsj.timetable.builder.types.AfterPerson;
import sk.vdsj.timetable.builder.types.AfterSchedule;
import sk.vdsj.timetable.builder.types.AfterTimetable;
import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Time;
import sk.vdsj.timetable.model.Timetable;

import java.util.ArrayList;

public class MethodChainingBuilder implements AfterTimetable, AfterSchedule, AfterEvent, AfterPerson {
    private static Timetable timetable;
    private static Schedule scheduleContext;
    private static Event eventContext;

    private static ArrayList<Schedule> schedules = new ArrayList<>();
    private static ArrayList<Event> events;
    private static ArrayList<String> persons;

    public static AfterTimetable timetable(String programme, String semester, String grade) {
        timetable = new Timetable(programme, semester, grade, null);
        return new MethodChainingBuilder();
    }

    @Override
    public AfterSchedule schedule(String title) {
        addPreviousSchedule();
        eventContext = null;
        scheduleContext = new Schedule(title, null);
        events = new ArrayList<>();

        return this;
    }

    @Override
    public AfterEvent event(String type, String time, String room, String groups, String note) {
        addPreviousEvent();
        eventContext = new Event(type, Time.valueOf(time), room, groups, null, note);
        persons = new ArrayList<>();

        return this;
    }

    @Override
    public AfterPerson person(String name) {
        persons.add(name);
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

        eventContext.setPersons(persons.toArray(new String[]{}));
        events.add(eventContext);
    }

    public static Timetable getTimeTable() {
        addPreviousSchedule();
        timetable.setSchedules(schedules.toArray(new Schedule[]{}));
        return timetable;
    }
}
