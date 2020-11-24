package sk.vdsj.timetable.antlr4.grammar;

import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Time;
import sk.vdsj.timetable.model.Timetable;

import java.util.ArrayList;
import java.util.List;

public class GrammarParserVisitor extends GrammarBaseVisitor<Void> {
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
    public Void visitTimetable(GrammarParser.TimetableContext ctx) {
        timetable = new Timetable(null, null, null, null);
        schedules = new ArrayList<>();
        events = new ArrayList<>();
        return super.visitTimetable(ctx);
    }

    @Override
    public Void visitTimetableName(GrammarParser.TimetableNameContext ctx) {
        String[] name = new String[ctx.getChildCount()];
        for (int x = 0; x < ctx.getChildCount(); x++) {
            name[x] = ctx.getChild(x).getText();
        }
        timetable.setProgramme(String.join(" ", name));
        super.visitTimetableName(ctx);
        return super.visitTimetableName(ctx);
    }

    @Override
    public Void visitTimetableYear(GrammarParser.TimetableYearContext ctx) {
        String halfYear = ctx.getChild(0).getText();
        timetable.setSemester(halfYear + " " + ctx.getText().substring(halfYear.length()));
        super.visitTimetableYear(ctx);
        return super.visitTimetableYear(ctx);
    }

    @Override
    public Void visitTimetableGrade(GrammarParser.TimetableGradeContext ctx) {
        timetable.setGrade(ctx.getText());
        return super.visitTimetableGrade(ctx);
    }

    @Override
    public Void visitSchedule(GrammarParser.ScheduleContext ctx) {
        schedule = new Schedule(null , null);
        schedules.add(schedule);
        timetable.setSchedules(schedules.toArray(new Schedule[0]));
        return super.visitSchedule(ctx);
    }

    @Override
    public Void visitScheduleName(GrammarParser.ScheduleNameContext ctx) {
        events = new ArrayList<>();
        String[] name = new String[ctx.getChildCount()];
        for (int x = 0; x < ctx.getChildCount(); x++) {
            name[x] = ctx.getChild(x).getText();
        }
        schedule.setTitle(String.join(" ", name));
        return super.visitScheduleName(ctx);
    }

    @Override
    public Void visitEvent(GrammarParser.EventContext ctx) {
        event = new Event(null, null, null, null, null, null);
        teachers = new ArrayList<>();
        events.add(event);
        schedule.setEvents(events.toArray(new Event[0]));
        return super.visitEvent(ctx);
    }

    @Override
    public Void visitTime(GrammarParser.TimeContext ctx) {
        String day = ctx.getChild(0).getText();
        event.setTime(Time.valueOf(day + " " + ctx.getText().substring(day.length())));
        return super.visitTime(ctx);
    }

    @Override
    public Void visitEventType(GrammarParser.EventTypeContext ctx) {
        event.setType(ctx.getText());
        return super.visitEventType(ctx);
    }

    @Override
    public Void visitEventGroups(GrammarParser.EventGroupsContext ctx) {
        event.setGroups(ctx.getText());
        return super.visitEventGroups(ctx);
    }

    @Override
    public Void visitRoom(GrammarParser.RoomContext ctx) {
        event.setLocation(ctx.getText());
        return super.visitRoom(ctx);
    }

    @Override
    public Void visitTeacher(GrammarParser.TeacherContext ctx) {
        String[] name = new String[ctx.getChildCount()];
        for (int x = 0; x < ctx.getChildCount(); x++) {
            name[x] = ctx.getChild(x).getText();
        }
        teachers.add(String.join(" ", name));
        event.setOrganisers(teachers.toArray(new String[0]));
        return super.visitTeacher(ctx);
    }

    @Override
    public Void visitNote(GrammarParser.NoteContext ctx) {
        String[] note = new String[ctx.getChildCount()];
        for (int x = 0; x < ctx.getChildCount(); x++) {
            note[x] = ctx.getChild(x).getText();
        }
        event.setNote(String.join(" ", note));
        return super.visitNote(ctx);
    }
}
