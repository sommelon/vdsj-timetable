package sk.vdsj.timetable.semantics;

import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Timetable;

public class TimetablePrinter {

    public void print(Timetable timetable) {
        System.out.println("Študijný program: " + timetable.getProgramme());
        System.out.println("Semester: " + timetable.getSemester());
        System.out.println("Ročník: " + timetable.getGrade());
        System.out.println("---------------------------------------------");

        for (Schedule schedule : timetable.getSchedules())
            print(schedule);
    }

    private void print(Schedule schedule) {
        System.out.println("Predmet: " + schedule.getTitle());

        for (Event event : schedule.getEvents())
            print(event);

    }

    private void print(Event event) {
        System.out.println("\tTyp hodiny: " + event.getType());
        System.out.print("\tVyučujúci: ");
        for (int i = 0; i < event.getPersons().length; i++) {
            if (i != 0) System.out.print("; ");
            System.out.print(event.getPersons()[i]);
        }
        System.out.println();
        System.out.println("\tTermín: " + event.getTime());
        System.out.println("\tMiestnosť: " + event.getRoom());
        System.out.println("\tSkupiny: " + event.getGroups());
        System.out.println("\tPoznámka: " + event.getNote());
        System.out.println();
    }

}
