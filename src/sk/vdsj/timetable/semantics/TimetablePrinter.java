package sk.vdsj.timetable.semantics;

import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Timetable;

public class TimetablePrinter {

    public void print(Timetable timetable) {
        System.out.println("Študijný program: " + timetable.getProgramme());
        System.out.println("Semester: " + timetable.getSemester());
        System.out.println("Rozsah semestra: " + timetable.getPeriod());
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
        System.out.print("\tTyp hodiny: " + event.getType());
        if (event.getInterval() != 1) {
            if (event.getInterval() < 5) {
                System.out.println(" (Každé " + event.getInterval() + " týždne)");
            } else {
                System.out.println(" (Každých " + event.getInterval() + " týždňov)");
            }
        } else {
            System.out.println();
        }

        System.out.print("\tVyučujúci: ");
        for (int i = 0; i < event.getOrganisers().length; i++) {
            if (i != 0) System.out.print("; ");
            System.out.print(event.getOrganisers()[i]);
        }
        System.out.println();
        System.out.println("\tTermín: " + event.getTime());
        System.out.println("\tMiestnosť: " + event.getLocation());
        if (event.getGroups() != null)
            System.out.println("\tSkupiny: " + event.getGroups());
        if (event.getNote() != null)
            System.out.println("\tPoznámka: " + event.getNote());
        System.out.println();
    }

}
