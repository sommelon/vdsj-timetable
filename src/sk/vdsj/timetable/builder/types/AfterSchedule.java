package sk.vdsj.timetable.builder.types;

public interface AfterSchedule {
    AfterEvent event(String type, String time, String room, String groups, String note);
}
