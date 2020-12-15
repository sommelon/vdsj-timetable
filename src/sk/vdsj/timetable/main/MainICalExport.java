package sk.vdsj.timetable.main;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.component.VVenue;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.MapTimeZoneCache;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import sk.vdsj.timetable.antlr4.grammar.GrammarLexer;
import sk.vdsj.timetable.antlr4.grammar.GrammarParser;
import sk.vdsj.timetable.antlr4.grammar.GrammarParserListener;
import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Timetable;
import sk.vdsj.timetable.semantics.TimetablePrinter;
import sk.vdsj.timetable.semantics.TimetableVelocityWebGenerator;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainICalExport {

    public static void main(String[] args) throws IOException {
        GrammarLexer lexer = new GrammarLexer(CharStreams.fromFileName("src/resources/examples/external_grammar_example_1.txt"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        ParseTree tree = parser.timetable();
        ParseTreeWalker treeWalker = new ParseTreeWalker();
        GrammarParserListener listener = new GrammarParserListener();
        treeWalker.walk(listener, tree);

        Timetable timetable = listener.getTimetable();
        timetable.validate();

        TimetablePrinter printer = new TimetablePrinter();
        printer.print(timetable);

        try (Writer writer = new FileWriter("timetable.html")) {
            TimetableVelocityWebGenerator htmlPrinter = new TimetableVelocityWebGenerator();
            htmlPrinter.generate(timetable, writer);
        }

        // ICal
        System.setProperty("net.fortuna.ical4j.timezone.cache.impl", MapTimeZoneCache.class.getName());
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        for(Schedule schedule:timetable.getSchedules()) {
            for(Event event: schedule.getEvents()) {
                calendar.getComponents().add(makeEvent(schedule.getTitle(), event,
                        timetable.getPeriod().getStart(), timetable.getPeriod().getEnd()));
            }
        }

        FileOutputStream fout = new FileOutputStream("mycalendar.ics");

        CalendarOutputter outputter = new CalendarOutputter();
        outputter.output(calendar, fout);
    }

    private static VEvent makeEvent(String name, Event event, LocalDate startOfSemester, LocalDate endOfSemester) {
        String recurrencePattern = "FREQ=WEEKLY;INTERVAL=" + event.getInterval() + ";BYSETPOS=1;UNTIL="
                + String.valueOf(endOfSemester.getYear()) + String.valueOf(endOfSemester.getMonthValue())
                + String.valueOf(endOfSemester.getDayOfMonth()) + "T000000Z";
        // Create a TimeZone
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timezone = registry.getTimeZone("Europe/Bratislava");
        VTimeZone tz = timezone.getVTimeZone();

        // Start Date
        java.util.Calendar startDate = new GregorianCalendar();
        startDate.setTimeZone(timezone);
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = startOfSemester.get(woy);
        startDate.set(java.util.Calendar.WEEK_OF_YEAR, weekNumber);
        startDate.set(java.util.Calendar.DAY_OF_WEEK, shiftDayOfWeek(event.getTime().getDay().ordinal()));
        startDate.set(java.util.Calendar.YEAR, startOfSemester.getYear());
        startDate.set(java.util.Calendar.HOUR_OF_DAY, event.getTime().getStartTime().getHour());
        startDate.set(java.util.Calendar.MINUTE, event.getTime().getStartTime().getMinute());
        startDate.set(java.util.Calendar.SECOND, event.getTime().getStartTime().getSecond());

        // End Date
        java.util.Calendar endDate = new GregorianCalendar();
        endDate.setTimeZone(timezone);
        endDate.set(java.util.Calendar.WEEK_OF_YEAR, weekNumber);
        endDate.set(java.util.Calendar.DAY_OF_WEEK, shiftDayOfWeek(event.getTime().getDay().ordinal()));
        endDate.set(java.util.Calendar.YEAR, startOfSemester.getYear());
        endDate.set(java.util.Calendar.HOUR_OF_DAY, event.getTime().getEndTime().getHour());
        endDate.set(java.util.Calendar.MINUTE, event.getTime().getEndTime().getHour());
        endDate.set(java.util.Calendar.SECOND, event.getTime().getEndTime().getHour());

        // Create the event
        String eventName = '(' + event.getType() + ") " + name;
        DateTime start = new DateTime(startDate.getTime());
        DateTime end = new DateTime(endDate.getTime());
        VEvent meeting = new VEvent(start, end, eventName);

        // add timezone info..
        meeting.getProperties().add(tz.getTimeZoneId());

        // generate unique identifier..
        UidGenerator ug = new RandomUidGenerator();
        Uid uid = ug.generateUid();
        meeting.getProperties().add(uid);

        // add attendees..
        for (String person: event.getOrganisers()) {
            Attendee dev1 = new Attendee();
            dev1.getParameters().add(Role.REQ_PARTICIPANT);
            dev1.getParameters().add(new Cn(person));
            meeting.getProperties().add(dev1);
        }
        // only one can be oragniser
        Organizer org = new Organizer();
        org.getParameters().add(new Cn(event.getOrganisers()[0]));
        meeting.getProperties().add(org);

        meeting.getProperties().add(new Location(event.getLocation()));

        try {
            meeting.getProperties().add(new RRule(recurrencePattern));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return meeting;
    }

    private static int shiftDayOfWeek(int x) {
        return (x + 2)%6;
    }

}
