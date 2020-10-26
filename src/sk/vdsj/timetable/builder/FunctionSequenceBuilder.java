package sk.vdsj.timetable.builder;

import sk.vdsj.timetable.model.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FunctionSequenceBuilder {
    private static String timetableProgramme;
    private static String timetableSemester;
    private static String timetableGrade;

    private static String scheduleTitle;
    private static List<Schedule> schedules = new ArrayList<>();

    private static List<Event> events;
    private static String eventType;
    private static Time formattedTime;
    private static String eventRoom;
    private static String eventGroups;
    private static String eventNote;

    private static List<String> persons;

    public static void timetable(String programme, String semester, String grade){
        timetableProgramme = programme;
        timetableSemester = semester;
        timetableGrade = grade;
    }

    public static void schedule(String title){
        if(scheduleTitle != null) createSchedule();
        scheduleTitle = title;
        events = new ArrayList<>();
    }

    public static void event(String type, String time, String room, String groups, String note){
        DayOfWeek day = DayOfWeek.valueOf(Days.valueOf(time.substring(0,time.indexOf(' '))).getKey());
        LocalTime startTime = LocalTime.parse(time.substring(time.indexOf(' ') + 1, time.indexOf('-')));
        LocalTime endTIme = LocalTime.parse(time.substring(time.indexOf('-') + 1));

        if(eventType != null) createEvent();
        eventType = type;
        formattedTime = new Time(day, startTime, endTIme);
        eventRoom = room;
        eventGroups = groups;
        eventNote = note;

        persons = new ArrayList<>();
    }

    public static void person(String name){
        persons.add(name);
    }

    public static Timetable getTimeTable() {
        createEvent();
        createSchedule();

        return new Timetable(timetableProgramme, timetableSemester, timetableGrade, schedules.toArray(new Schedule[] {}));
    }

    private static void createSchedule() {
        schedules.add(new Schedule(scheduleTitle, events.toArray(new Event[]{})));
    }

    private static void createEvent() {
        events.add(new Event(eventType, formattedTime, eventRoom, eventGroups, persons.toArray(new String[]{}), eventNote));
    }

}
