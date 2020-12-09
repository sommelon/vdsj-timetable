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
        System.out.print("Predmet: " + schedule.getTitle());
        if (schedule.getPeriod() != 1) {
            if (schedule.getPeriod() < 5) {
                System.out.println(" (Každé " + schedule.getPeriod() + " týždne)");
            } else {
                System.out.println(" (Každých " + schedule.getPeriod() + " týždňov)");
            }
        } else {
            System.out.println();
        }

        for (Event event : schedule.getEvents())
            print(event);

    }

    private void print(Event event) {
        System.out.println("\tTyp hodiny: " + event.getType());
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
