package sk.vdsj.timetable.builder.types;

public interface AfterTimetable {
    AfterSchedule schedule(String title);
    AfterSchedule schedule(String title, int period);
}
