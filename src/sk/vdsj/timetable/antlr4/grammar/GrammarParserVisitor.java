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


}
