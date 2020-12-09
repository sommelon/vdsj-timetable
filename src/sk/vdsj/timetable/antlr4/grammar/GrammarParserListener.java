package sk.vdsj.timetable.antlr4.grammar;

import org.antlr.v4.runtime.ParserRuleContext;
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
        timetable = new Timetable(null, null, null, null, null);
        schedules = new ArrayList<>();
        super.enterTimetable(ctx);
    }

    @Override
    public void enterProgramme(GrammarParser.ProgrammeContext ctx) {
        timetable.setProgramme(getSeparatedText(ctx));
        super.enterProgramme(ctx);
    }

    @Override
    public void enterSemester(GrammarParser.SemesterContext ctx) {
        String halfYear = ctx.getChild(0).getText();
        timetable.setSemester(halfYear + " " + ctx.getText().substring(halfYear.length()));
        super.enterSemester(ctx);
    }

    @Override
    public void enterGrade(GrammarParser.GradeContext ctx) {
        timetable.setGrade(ctx.getText());
        super.enterGrade(ctx);
    }

    @Override
    public void enterSchedule(GrammarParser.ScheduleContext ctx) {
        schedule = new Schedule(null , null);
        super.enterSchedule(ctx);
    }

    @Override
    public void enterScheduleTitle(GrammarParser.ScheduleTitleContext ctx) {
        events = new ArrayList<>();
        schedule.setTitle(getSeparatedText(ctx));
        super.enterScheduleTitle(ctx);
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
    public void enterGroups(GrammarParser.GroupsContext ctx) {
        event.setGroups(ctx.getText());
        super.enterGroups(ctx);
    }

    @Override
    public void enterTime(GrammarParser.TimeContext ctx) {
        String day = ctx.getChild(0).getText();
        event.setTime(Time.valueOf(day + " " + ctx.getText().substring(day.length())));
        super.enterTime(ctx);
    }

    @Override
    public void enterLocation(GrammarParser.LocationContext ctx) {
        event.setLocation(ctx.getText());
        super.enterLocation(ctx);
    }

    @Override
    public void enterOrganiser(GrammarParser.OrganiserContext ctx) {
        teachers.add(getSeparatedText(ctx));
        super.enterOrganiser(ctx);
    }

    @Override
    public void enterNote(GrammarParser.NoteContext ctx) {
        event.setNote(getSeparatedText(ctx));
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

    private String getSeparatedText(ParserRuleContext ctx) {
        String[] word = new String[ctx.getChildCount()];
        for (int x = 0; x < ctx.getChildCount(); x++) {
            word[x] = ctx.getChild(x).getText();
        }
        return String.join(" ", word);
    }
}
