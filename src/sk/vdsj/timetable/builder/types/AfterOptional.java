package sk.vdsj.timetable.builder.types;

public interface AfterOptional extends AfterSchedule, AfterTimetable {
    AfterOptional groups(String groups);
    AfterOptional note(String note);
    AfterOptional interval(int interval);
}
