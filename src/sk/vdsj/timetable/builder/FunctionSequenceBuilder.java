package sk.vdsj.timetable.builder;

import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Time;
import sk.vdsj.timetable.model.Timetable;

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
        if(eventType != null)
            events.add(new Event(type, Time.valueOf(time), room, groups, persons.toArray(new String[]{}), note));
        eventType = type;

        persons = new ArrayList<>();
    }

    public static void person(String name){
        persons.add(name);
    }

    public static Timetable getTimeTable() {
        createSchedule();
        return new Timetable(timetableProgramme, timetableSemester, timetableGrade, schedules.toArray(new Schedule[] {}));
    }

    private static void createSchedule() {
        schedules.add(new Schedule(scheduleTitle, events.toArray(new Event[]{})));
    }

}
