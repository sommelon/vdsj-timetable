package sk.vdsj.timetable.antlr4.grammar;

import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Time;
import sk.vdsj.timetable.model.Timetable;

import java.util.ArrayList;
import java.util.List;

public class GrammarParserListener extends GrammarBaseListener {
    private Timetable timetable;
    private List<Schedule> schedules;
    private Schedule schedule;
    private List<Event> events;
    private Event event;
    private List<String> teachers;

    @Override
    public void enterTimetable(GrammarParser.TimetableContext ctx) {
        timetable = new Timetable(null, null, null, null);
        schedules = new ArrayList<>();
        super.enterTimetable(ctx);
    }

    @Override
    public void enterTimetableName(GrammarParser.TimetableNameContext ctx) {
        String[] name = new String[ctx.getChildCount()];
        for (int x = 0; x < ctx.getChildCount(); x++) {
            name[x] = ctx.getChild(x).getText();
        }
        timetable.setProgramme(String.join(" ", name));
        super.enterTimetableName(ctx);
    }

    @Override
    public void enterTimetableYear(GrammarParser.TimetableYearContext ctx) {
        String halfYear = ctx.getChild(0).getText();
        timetable.setSemester(halfYear + " " + ctx.getText().substring(halfYear.length()));
        super.enterTimetableYear(ctx);
    }

    @Override
    public void enterTimetableGrade(GrammarParser.TimetableGradeContext ctx) {
        timetable.setGrade(ctx.getText());
        super.enterTimetableGrade(ctx);
    }

    @Override
    public void enterSchedule(GrammarParser.ScheduleContext ctx) {
        schedule = new Schedule(null , null);
        super.enterSchedule(ctx);
    }

    @Override
    public void enterScheduleName(GrammarParser.ScheduleNameContext ctx) {
        events = new ArrayList<>();
        String[] name = new String[ctx.getChildCount()];
        for (int x = 0; x < ctx.getChildCount(); x++) {
            name[x] = ctx.getChild(x).getText();
        }
        schedule.setTitle(String.join(" ", name));
        super.enterScheduleName(ctx);
    }

    @Override
    public void enterEvent(GrammarParser.EventContext ctx) {
        event = new Event(null, null, null, null, null, null);
        teachers = new ArrayList<>();
        super.enterEvent(ctx);
    }

    @Override
    public void enterEventType(GrammarParser.EventTypeContext ctx) {
        event.setType(ctx.getText());
        super.enterEventType(ctx);
    }

    @Override
    public void enterEventGroups(GrammarParser.EventGroupsContext ctx) {
        event.setGroups(ctx.getText());
        super.enterEventGroups(ctx);
    }

    @Override
    public void enterTime(GrammarParser.TimeContext ctx) {
        String day = ctx.getChild(0).getText();
        event.setTime(Time.valueOf(day + " " + ctx.getText().substring(day.length())));
        super.enterTime(ctx);
    }

    @Override
    public void enterRoom(GrammarParser.RoomContext ctx) {
        event.setLocation(ctx.getText());
        super.enterRoom(ctx);
    }

    @Override
    public void enterTeacher(GrammarParser.TeacherContext ctx) {
        String[] name = new String[ctx.getChildCount()];
        for (int x = 0; x < ctx.getChildCount(); x++) {
            name[x] = ctx.getChild(x).getText();
        }
        teachers.add(String.join(" ", name));
        super.enterTeacher(ctx);
    }

    @Override
    public void enterNote(GrammarParser.NoteContext ctx) {
        String[] note = new String[ctx.getChildCount()];
        for (int x = 0; x < ctx.getChildCount(); x++) {
            note[x] = ctx.getChild(x).getText();
        }
        event.setNote(String.join(" ", note));
        super.enterNote(ctx);
    }

    @Override
    public void exitEvent(GrammarParser.EventContext ctx) {
        event.setOrganisers(teachers.toArray(new String[0]));
        events.add(event);
        super.exitEvent(ctx);
    }

    @Override
    public void exitSchedule(GrammarParser.ScheduleContext ctx) {
        schedule.setEvents(events.toArray(new Event[0]));
        schedules.add(schedule);
        super.exitSchedule(ctx);
    }

    @Override
    public void exitTimetable(GrammarParser.TimetableContext ctx) {
        timetable.setSchedules(schedules.toArray(new Schedule[0]));
        super.exitTimetable(ctx);
    }

    public Timetable getTimetable() {
        return timetable;
    }
}
