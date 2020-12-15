package sk.vdsj.timetable.builder.types;

public interface AfterOrganiser extends AfterSchedule, AfterEvent, AfterTimetable {
    AfterOptional groups(String groups);
    AfterOptional note(String note);
    AfterOptional interval(int interval);
}
