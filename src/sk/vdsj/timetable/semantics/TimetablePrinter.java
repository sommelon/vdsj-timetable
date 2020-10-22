package sk.vdsj.timetable.semantics;

import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Timetable;

public class TimetablePrinter {

    public void print(Timetable timetable) {
        System.out.println("Študijný program: " + timetable.getProgramme());

        for(Event event : timetable.getEvents())
            print(event);
    }

    private void print(Event event) {
        System.out.println("Predmet: " + event.getTitle());

        for(Schedule schedule : event.getSchedules())
            print(schedule);

    }

    private void print(Schedule schedule) {
        System.out.println("Typ hodiny: " + schedule.getType());
        System.out.print("Vyučujúci: ");
        for(int i = 0; i < schedule.getPersons().length; i++) {
            if(i != 0) System.out.print(", ");
            System.out.print(schedule.getPersons()[i]);
        }
        System.out.println();
        System.out.println("Termín: " + schedule.getTime());
        System.out.println("Skupiny: " + schedule.getGroups());
        System.out.println("Poznámka: " + schedule.getNote());
        System.out.println();
    }

}
