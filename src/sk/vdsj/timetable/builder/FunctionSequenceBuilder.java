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

    private static String eventTitle;
    private static List<Event> events = new ArrayList<>();

    private static List<Schedule> schedules;
    private static String scheduleType;
    private static List<String> persons;


    public static void timetable(String programme, String semester, String grade){
        timetableProgramme = programme;
        timetableSemester = semester;
        timetableGrade = grade;
    }

    public static void event(String title){
        if(eventTitle != null) createEvent();
        eventTitle = title;
        schedules = new ArrayList<>();
    }

    public static void schedule(String type, String time, String room, String groups, String note){
        DayOfWeek day = DayOfWeek.valueOf(Days.valueOf(time.substring(0,time.indexOf(' '))).getKey());
        LocalTime startTime = LocalTime.parse(time.substring(time.indexOf(' ') + 1, time.indexOf('-')));
        LocalTime endTIme = LocalTime.parse(time.substring(time.indexOf('-') + 1));
        Time formattedTime = new Time(day, startTime, endTIme);

        if(scheduleType != null)
            schedules.add(new Schedule(type,  formattedTime, room, groups, persons.toArray(new String[]{}), note));
        scheduleType = type;

        persons = new ArrayList<>();
    }

    public static void person(String name){
        persons.add(name);
    }

    public static Timetable getTimeTable() {
        createEvent();
        return new Timetable(timetableProgramme, timetableSemester, timetableGrade, events.toArray(new Event[] {}));
    }

    private static void createEvent() {
        events.add(new Event(eventTitle, schedules.toArray(new Schedule[]{})));
    }

}
