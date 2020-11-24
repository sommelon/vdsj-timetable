package sk.vdsj.timetable.antlr4.grammar;

import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Timetable;

import java.util.ArrayList;
import java.util.List;

public class GrammarParserVisitor extends GrammarBaseVisitor {
    private Timetable timetable;
    private List<Schedule> schedules;
    private Schedule schedule;
    private List<Event> events;
    private Event event;
    private List<String> teachers;

    public Timetable getTimetable() {
        return timetable;
    }

    @Override
    public Object visitTimetable(GrammarParser.TimetableContext ctx) {
        timetable = new Timetable(null, null, null, null);
        schedules = new ArrayList<>();
        return super.visitTimetable(ctx);
    }

    @Override
    public Object visitTimetableHeader(GrammarParser.TimetableHeaderContext ctx) {
        return super.visitTimetableHeader(ctx);
    }

    @Override
    public Object visitTimetableName(GrammarParser.TimetableNameContext ctx) {
        return super.visitTimetableName(ctx);
    }

    @Override
    public Object visitTimetableYear(GrammarParser.TimetableYearContext ctx) {
        return super.visitTimetableYear(ctx);
    }

    @Override
    public Object visitTimetableGrade(GrammarParser.TimetableGradeContext ctx) {
        return super.visitTimetableGrade(ctx);
    }

    @Override
    public Object visitSchedule(GrammarParser.ScheduleContext ctx) {
        return super.visitSchedule(ctx);
    }

    @Override
    public Object visitScheduleName(GrammarParser.ScheduleNameContext ctx) {
        return super.visitScheduleName(ctx);
    }

    @Override
    public Object visitEvent(GrammarParser.EventContext ctx) {
        return super.visitEvent(ctx);
    }

    @Override
    public Object visitTime(GrammarParser.TimeContext ctx) {
        return super.visitTime(ctx);
    }

    @Override
    public Object visitEventType(GrammarParser.EventTypeContext ctx) {
        return super.visitEventType(ctx);
    }

    @Override
    public Object visitEventGroups(GrammarParser.EventGroupsContext ctx) {
        return super.visitEventGroups(ctx);
    }

    @Override
    public Object visitDay(GrammarParser.DayContext ctx) {
        return super.visitDay(ctx);
    }

    @Override
    public Object visitTime_from(GrammarParser.Time_fromContext ctx) {
        return super.visitTime_from(ctx);
    }

    @Override
    public Object visitTime_to(GrammarParser.Time_toContext ctx) {
        return super.visitTime_to(ctx);
    }

    @Override
    public Object visitRoom(GrammarParser.RoomContext ctx) {
        return super.visitRoom(ctx);
    }

    @Override
    public Object visitTeacher(GrammarParser.TeacherContext ctx) {
        return super.visitTeacher(ctx);
    }

    @Override
    public Object visitNote(GrammarParser.NoteContext ctx) {
        return super.visitNote(ctx);
    }
}
