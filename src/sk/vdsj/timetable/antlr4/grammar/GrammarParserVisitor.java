package sk.vdsj.timetable.antlr4.grammar;

import org.antlr.v4.runtime.ParserRuleContext;
import sk.vdsj.timetable.model.*;

public class GrammarParserVisitor extends GrammarBaseVisitor<Object> {

    @Override
    public Timetable visitTimetable(GrammarParser.TimetableContext ctx) {
        String programme = (String) visit(ctx.timetableHeader().programme());
        String semester = (String) visit(ctx.timetableHeader().semester());
        String grade = (String) visit(ctx.timetableHeader().grade());
        DateRange period = (DateRange) visit(ctx.timetableHeader().period());

        Schedule[] schedules = new Schedule[ctx.schedule().size()];
        for (int i = 0; i < ctx.schedule().size(); i++) {
            schedules[i] = (Schedule) visit(ctx.schedule(i));
        }

        return new Timetable(programme, semester, period, grade, schedules);
    }

    @Override
    public String visitProgramme(GrammarParser.ProgrammeContext ctx) {
        return getSeparatedText(ctx);
    }

    @Override
    public String visitSemester(GrammarParser.SemesterContext ctx) {
        return ctx.STRING().getText() + " "
                + ctx.NUMBER(0).getText() + ctx.getChild(2).getText() + ctx.NUMBER(1).getText();
    }

    @Override
    public DateRange visitPeriod(GrammarParser.PeriodContext ctx) {
        return DateRange.valueOf(ctx.getText());
    }

    @Override
    public String visitGrade(GrammarParser.GradeContext ctx) {
        return ctx.getText();
    }

    @Override
    public Schedule visitSchedule(GrammarParser.ScheduleContext ctx) {
        Event[] events = new Event[ctx.event().size()];
        for (int i = 0; i < ctx.event().size(); i++) {
            events[i] = (Event) visit(ctx.event(i));
        }

        return new Schedule((String) visit(ctx.scheduleTitle()), events);
    }

    @Override
    public String visitScheduleTitle(GrammarParser.ScheduleTitleContext ctx) {
        return getSeparatedText(ctx);
    }

    @Override
    public Event visitEvent(GrammarParser.EventContext ctx) {
        String type = (String) visit(ctx.eventType());
        Time time = (Time) visit(ctx.time());
        String location = (String) visit(ctx.location());
        String groups = ctx.groups() != null ? (String) visit(ctx.groups()) : null;

        String[] organisers = new String[ctx.organiser().size()];
        for (int i = 0; i < ctx.organiser().size(); i++) {
            organisers[i] = (String) visit(ctx.organiser(i));
        }
        String note = ctx.note() != null ? (String) visit(ctx.note()) : null;
        int interval = ctx.interval() != null ? (Integer) visit(ctx.interval()) : 1;
        return new Event(type, time, location, groups, organisers, note, interval);
    }

    @Override
    public String visitEventType(GrammarParser.EventTypeContext ctx) {
        return ctx.getText();
    }

    @Override
    public Integer visitInterval(GrammarParser.IntervalContext ctx) {
        return Integer.parseInt(ctx.getChild(0).getText());
    }

    @Override
    public Time visitTime(GrammarParser.TimeContext ctx) {
        return Time.valueOf(ctx.day().getText() + " "
                + ctx.time_from().getText() + ctx.getChild(2).getText() + ctx.time_to().getText());
    }

    @Override
    public String visitLocation(GrammarParser.LocationContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitGroups(GrammarParser.GroupsContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitOrganiser(GrammarParser.OrganiserContext ctx) {
        return getSeparatedText(ctx).replace(" .", ".");
    }

    @Override
    public String visitNote(GrammarParser.NoteContext ctx) {
        return getSeparatedText(ctx);
    }

    private String getSeparatedText(ParserRuleContext ctx) {
        String[] word = new String[ctx.getChildCount()];
        for (int x = 0; x < ctx.getChildCount(); x++) {
            word[x] = ctx.getChild(x).getText();
        }
        return String.join(" ", word);
    }
}
