package sk.vdsj.timetable.builder.types;

public interface AfterSchedule {
    AfterEvent event(String type, String time, String location, String groups, String note);
    AfterEvent event(String type, String time, String location, String groups);
    // Can't overload this, therefore `groups` is required if you want a note
    // AfterEvent event(String type, String time, String location, String note);
    AfterEvent event(String type, String time, String location);
}
